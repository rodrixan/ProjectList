package com.example.rodri.projectlist.project.repository

import androidx.lifecycle.LiveData
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectDetails
import com.example.rodri.projectlist.common.rest.model.ProjectListItem

interface ProjectRepository {
    val projectList: LiveData<AppInternalData<List<ProjectListItem>>>
    val projectDetails: LiveData<AppInternalData<ProjectDetails>>
    fun loadProjectList(userId: String)
    fun loadProjectDetails(userId: String, projectName: String)
}