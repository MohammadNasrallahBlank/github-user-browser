package com.blank.github_browser.data

import com.google.gson.annotations.SerializedName

data class GitHubUserSearchResponse(
    @SerializedName("items")
    val items: List<GitHubUser>
)
