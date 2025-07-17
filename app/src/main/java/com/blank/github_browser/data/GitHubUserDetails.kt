package com.blank.github_browser.data

import com.google.gson.annotations.SerializedName

data class GitHubUserDetails(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val fullName: String?,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int
)
