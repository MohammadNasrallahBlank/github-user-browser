package com.blank.github_browser.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blank.github_browser.data.GitHubRepositoryData
import com.blank.github_browser.data.GitHubUserDetails
import com.blank.github_browser.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepositoryViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserRepositoryUiState>(UserRepositoryUiState.Loading)
    val uiState: StateFlow<UserRepositoryUiState> = _uiState

    fun loadUserAndRepos(username: String) {
        viewModelScope.launch {
            _uiState.value = UserRepositoryUiState.Loading

            try {
                val user = userRepository.getUserDetails(username)
                val repos = userRepository.getUserRepos(username)
                _uiState.value = UserRepositoryUiState.Success(user, repos)
            } catch (e: Exception) {
                _uiState.value = UserRepositoryUiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}

sealed class UserRepositoryUiState {
    object Loading : UserRepositoryUiState()
    data class Success(
        val user: GitHubUserDetails,
        val repos: List<GitHubRepositoryData>
    ) : UserRepositoryUiState()

    data class Error(val message: String) : UserRepositoryUiState()
}
