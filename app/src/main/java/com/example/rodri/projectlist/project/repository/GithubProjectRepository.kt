package com.example.rodri.projectlist.project.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.rodri.projectlist.common.rest.api.ProjectApi
import com.example.rodri.projectlist.common.rest.callback.ResponseCallback
import com.example.rodri.projectlist.common.rest.callback.project.ProjectApiServiceCallback
import com.example.rodri.projectlist.common.rest.model.ErrorResponse
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.LiveDataWrapper
import com.example.rodri.projectlist.common.rest.service.ProjectApiService
import timber.log.Timber

class GithubProjectRepository : ProjectRepository {

    private val projectService: ProjectApi by lazy {
        ProjectApiService.getApi()
    }

    val projectListData = MutableLiveData<LiveDataWrapper<List<ProjectListItem>>>()

    override fun getProjectList(userId: String): LiveData<LiveDataWrapper<List<ProjectListItem>>> {

        projectService.getProjectList(userId)
            .enqueue(ProjectApiServiceCallback(object :
                ResponseCallback<List<ProjectListItem>> {
                override fun onResponseOK(responseData: List<ProjectListItem>) {
                    Timber.d("Received ${responseData.size} projects")
                    projectListData.value = wrapProjectListResponse(data = responseData)
                }

                override fun onResponseKO(errorResponse: ErrorResponse) {
                    Timber.d("Error! $errorResponse")
                    projectListData.value = wrapProjectListResponse(error = errorResponse)

                }
            }))
        return projectListData
    }

    private fun <T> wrapProjectListResponse(
        data: T? = null,
        error: ErrorResponse? = null
    ): LiveDataWrapper<T> = LiveDataWrapper(data == null, data, error)
}