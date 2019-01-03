package com.example.rodri.projectlist.project.repository

import androidx.lifecycle.LiveData
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectListItem

interface ProjectRepository {
    fun getProjectList(userId: String): LiveData<AppInternalData<List<ProjectListItem>>>
}