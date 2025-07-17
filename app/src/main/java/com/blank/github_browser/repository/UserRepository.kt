package com.blank.github_browser.repository

import com.blank.github_browser.api_service.GitHubApi
import com.blank.github_browser.data.GitHubRepositoryData
import com.blank.github_browser.data.GitHubUserDetails
import javax.inject.Inject

interface UserRepository {
    suspend fun getUserDetails(username: String): GitHubUserDetails
    suspend fun getUserRepos(username: String): List<GitHubRepositoryData>
}

class UserRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : UserRepository {

    override suspend fun getUserDetails(username: String): GitHubUserDetails {
        return api.getUserDetails(username)
    }

    override suspend fun getUserRepos(username: String): List<GitHubRepositoryData> {
        return api.getUserRepositories(username)
            .filterNot { it.fork }
    }
}
