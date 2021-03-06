package com.example.rodri.projectlist.project.repository

import androidx.lifecycle.MutableLiveData
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectDetails
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
import java.util.*

class GithubProjectRepository : ProjectRepository {


    override val projectList = MutableLiveData<AppInternalData<List<ProjectListItem>>>()
    override val projectDetails = MutableLiveData<AppInternalData<ProjectDetails>>()

    val dummyList = listOf(
        ProjectListItem(0, "Test1", User(), 1, "Java"),
        ProjectListItem(1, "Test2", User(), 4, "Python"),
        ProjectListItem(0, "Test3", User(), 20, "C++"),
        ProjectListItem(0, "Test3", User(), 20, "JavaScript")
    )

    val dummyDetail = ProjectDetails(
        name = "Test1",
        owner = User(
            name = "User42",
            avatarUrl = "https://i.kym-cdn.com/entries/icons/original/000/000/635/1260585284155_copy.jpg"
        ),
        description = "Test project for showing purposes",
        creationDate = Date(),
        lastUpdateDate = Date(),
        cloneUrl = "https://fake.clone.url.com",
        watchersCount = 23,
        language = "Kotlin",
        hasIssues = false,
        openIssuesCount = 0
    )

    override fun loadProjectList(userId: String) {

        //only make a call if it's not already loading
        if (projectList.value !is AppInternalData.Loading) {
            projectList.value = AppInternalData.Loading

            GlobalScope.launch {
                Timber.d("Calling for $userId projects...")

                val apiResponse =
                    ProjectApiService.serviceApi.getProjectList(userId)
                        .awaitForResult { ProjectApiService.serviceResponseToAppError(it) }
                Timber.d("Received response: success? ${apiResponse is AppInternalData.Success}")
                withContext(Dispatchers.Main) {
                    projectList.value =
                            if (apiResponse is AppInternalData.Error && apiResponse.code == HttpURLConnection.HTTP_FORBIDDEN) {
                                AppInternalData.Success(dummyList)
                            } else {
                                apiResponse
                            }
                }
            }
        }
    }

    override fun loadProjectDetails(userId: String, projectName: String) {
        if (projectDetails.value !is AppInternalData.Loading) {
            projectDetails.value = AppInternalData.Loading

            GlobalScope.launch {
                Timber.d("Calling for $userId $projectName project details...")

                val apiResponse =
                    ProjectApiService.serviceApi.getProjectDetails(userId, projectName)
                        .awaitForResult { ProjectApiService.serviceResponseToAppError(it) }
                Timber.d("Received response: success? ${apiResponse is AppInternalData.Success}")
                withContext(Dispatchers.Main) {
                    projectDetails.value =
                            if (apiResponse is AppInternalData.Error && apiResponse.code == HttpURLConnection.HTTP_FORBIDDEN) {
                                AppInternalData.Success(dummyDetail)
                            } else {
                                apiResponse
                            }
                }
            }
        }
    }
}