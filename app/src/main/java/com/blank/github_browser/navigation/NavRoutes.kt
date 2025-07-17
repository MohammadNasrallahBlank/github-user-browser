package com.blank.github_browser.navigation

// ui/navigation/NavRoutes.kt
sealed class NavRoutes(val route: String) {
    object UserList : NavRoutes("user_list")
    object UserRepos : NavRoutes("user_repos/{username}") {
        fun createRoute(username: String) = "user_repos/$username"
    }
}
