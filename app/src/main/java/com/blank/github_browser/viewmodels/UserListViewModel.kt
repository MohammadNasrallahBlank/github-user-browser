package com.blank.github_browser.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blank.github_browser.data.GitHubUser
import com.blank.github_browser.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Idle)
    val uiState: StateFlow<UserListUiState> = _uiState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    init {
        viewModelScope.launch {
            _query
                .debounce(300)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collectLatest { searchUsers(it) }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    private suspend fun searchUsers(query: String) {
        _uiState.value = UserListUiState.Loading
        try {
            val users = repository.searchUsers(query)
            _uiState.value = UserListUiState.Success(users)
        } catch (e: Exception) {
            _uiState.value = UserListUiState.Error(e.message ?: "Unknown error")
        }
    }
}

sealed class UserListUiState {
    object Idle : UserListUiState()
    object Loading : UserListUiState()
    data class Success(val users: List<GitHubUser>) : UserListUiState()
    data class Error(val message: String) : UserListUiState()
}

