package com.example.rodri.projectlist.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.rodri.projectlist.rest.api.ProjectApi
import com.example.rodri.projectlist.rest.callback.ResponseCallback
import com.example.rodri.projectlist.rest.callback.project.ProjectApiServiceCallback
import com.example.rodri.projectlist.rest.model.ErrorResponse
import com.example.rodri.projectlist.rest.model.ProjectListItem
import com.example.rodri.projectlist.rest.service.ProjectApiService
import timber.log.Timber

class GithubProjectRepository : ProjectRepository {

    private val projectService: ProjectApi by lazy {
        ProjectApiService.getApi()
    }

    override fun getProjectList(userId: String): LiveData<List<ProjectListItem>> {
        val projectListData = MutableLiveData<List<ProjectListItem>>()
        projectService.getProjectList(userId)
            .enqueue(ProjectApiServiceCallback(object : ResponseCallback<List<ProjectListItem>> {
                override fun onResponseOK(responseData: List<ProjectListItem>) {
                    Timber.d("Received ${responseData.size} projects")
                    projectListData.value = responseData
                }

                override fun onResponseKO(errorResponse: ErrorResponse) {
                    Timber.d("Error! $errorResponse")
                }
            }))
        return projectListData
    }
}