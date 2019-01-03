package com.example.rodri.projectlist.project.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.rest.model.User
import com.example.rodri.projectlist.common.rest.service.ProjectApiService
import com.example.rodri.projectlist.common.rest.service.awaitForResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.net.HttpURLConnection

class GithubProjectRepository : ProjectRepository {

    val projectListData = MutableLiveData<AppInternalData<List<ProjectListItem>>>()

    val dummyList = listOf(
        ProjectListItem(0, "Test1", User(), 1, "Java"),
        ProjectListItem(1, "Test2", User(), 4, "Python"),
        ProjectListItem(0, "Test3", User(), 20, "C++"),
        ProjectListItem(0, "Test3", User(), 20, "JavaScript")
    )

    override fun getProjectList(userId: String): LiveData<AppInternalData<List<ProjectListItem>>> {

        //only make a call if it's not already loading
        if (projectListData.value !is AppInternalData.Loading) {
            projectListData.value = AppInternalData.Loading()

            GlobalScope.launch {
                Timber.d("Calling for $userId projects...")

                val apiResponse =
                    ProjectApiService.serviceApi.getProjectList(userId)
                        .awaitForResult { ProjectApiService.serviceResponseToAppError(it) }
                Timber.d("Received response: success? ${apiResponse is AppInternalData.Success}")
                withContext(Dispatchers.Main) {
                    projectListData.value = if(apiResponse is AppInternalData.Error && apiResponse.code==HttpURLConnection.HTTP_FORBIDDEN){
                        AppInternalData.Success(dummyList)
                    }else {
                        apiResponse
                    }
                }
            }
        }

        return projectListData
    }
}