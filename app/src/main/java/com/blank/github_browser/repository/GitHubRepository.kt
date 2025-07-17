package com.blank.github_browser.repository

import com.blank.github_browser.api_service.GitHubApi
import com.blank.github_browser.data.GitHubUser
import javax.inject.Inject

interface GitHubRepository {
    suspend fun searchUsers(query: String): List<GitHubUser>
}

class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : GitHubRepository {
    override suspend fun searchUsers(query: String): List<GitHubUser> {
        return api.searchUsers(query).items
    }
}

