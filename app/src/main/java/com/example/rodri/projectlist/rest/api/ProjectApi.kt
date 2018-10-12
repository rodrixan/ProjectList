package com.example.rodri.projectlist.rest.api

import com.example.rodri.projectlist.rest.model.ProjectDetails
import com.example.rodri.projectlist.rest.model.ProjectListItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectApi {
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<ProjectListItem>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<ProjectDetails>

}