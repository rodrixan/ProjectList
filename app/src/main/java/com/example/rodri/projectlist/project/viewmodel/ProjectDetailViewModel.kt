package com.example.rodri.projectlist.project.viewmodel

import androidx.lifecycle.LiveData
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectDetails

interface ProjectDetailViewModel {
    fun getProjectDetails(projectName: String): LiveData<AppInternalData<ProjectDetails>>
}