package com.blank.github_browser.data

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
