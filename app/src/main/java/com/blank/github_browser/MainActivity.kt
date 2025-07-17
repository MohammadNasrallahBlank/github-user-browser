package com.blank.github_browser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.blank.github_browser.navigation.NavRoutes
import com.blank.github_browser.screens.UserListScreen
import com.blank.github_browser.screens.UserRepositoryScreen
import com.blank.github_browser.ui.theme.Github_browserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Github_browserTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.UserList.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavRoutes.UserList.route) {
                            UserListScreen(
                                onUserClick = { user ->
                                    navController.navigate(
                                        NavRoutes.UserRepos.createRoute(user.login)
                                    )
                                }
                            )
                        }

                        composable(
                            route = NavRoutes.UserRepos.route,
                            arguments = listOf(navArgument("username") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val username = backStackEntry.arguments?.getString("username") ?: ""
                            UserRepositoryScreen(username)
                        }
                    }
                }
            }
        }
    }
}

