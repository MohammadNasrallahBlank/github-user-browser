package com.blank.github_browser.data

import com.google.gson.annotations.SerializedName

data class GitHubRepositoryData(
    val name: String,
    @SerializedName("language") val language: String?,
    @SerializedName("stargazers_count") val stars: Int,
    val description: String?,
    val fork: Boolean,
    @SerializedName("html_url") val htmlUrl: String
)
