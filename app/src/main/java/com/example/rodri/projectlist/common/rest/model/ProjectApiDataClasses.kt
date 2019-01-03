package com.example.rodri.projectlist.common.rest.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProjectListItem(
    @SerializedName("id") var id: Long = 0,
    @SerializedName("name") var name: String,
    @SerializedName("owner") var owner: User? = null,
    @SerializedName("watchers") var watchersCount: Int = 0,
    @SerializedName("language") var language: String? = null
)

data class ProjectDetails(
    @SerializedName("id") var id: Long = 0,
    @SerializedName("name") var name: String,
    @SerializedName("owner") var owner: User? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("created_at") var creationDate: Date? = null,
    @SerializedName("updated_at") var lastUpdateDate: Date? = null,
    @SerializedName("clone_url") var cloneUrl: String? = null,
    @SerializedName("watchers") var watchersCount: Int = 0,
    @SerializedName("language") var language: String? = null,
    @SerializedName("has_issues") var hasIssues: Boolean = false,
    @SerializedName("open_issues") var openIssuesCount: Int = 0
)

data class User(
    @SerializedName("login") var name: String? = null,
    @SerializedName("id") var id: Long = 0,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
    @SerializedName("email") var email: String? = null
)

data class ProjectApiErrorResponse(@SerializedName("message") var errorMessage: String? = "")