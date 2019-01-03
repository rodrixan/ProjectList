package com.example.rodri.projectlist.common.rest.api

import com.example.rodri.projectlist.common.rest.model.ProjectDetails
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectApi {
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Deferred<Response<List<ProjectListItem>>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<ProjectDetails>

}