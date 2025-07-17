package com.blank.github_browser.api_service

import com.blank.github_browser.data.GitHubRepositoryData
import com.blank.github_browser.data.GitHubUserDetails
import com.blank.github_browser.data.GitHubUserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): GitHubUserSearchResponse

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): GitHubUserDetails

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String
    ): List<GitHubRepositoryData>
}

