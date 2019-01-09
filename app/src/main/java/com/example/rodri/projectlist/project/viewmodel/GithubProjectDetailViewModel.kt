package com.example.rodri.projectlist.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectDetails
import com.example.rodri.projectlist.project.repository.ProjectRepository

class GithubProjectDetailViewModel(private val projectRepository: ProjectRepository) : ViewModel(),
    ProjectDetailViewModel {
    override var currentProjectDetails: LiveData<AppInternalData<ProjectDetails>> =
        MutableLiveData<AppInternalData<ProjectDetails>>()

    override fun loadProjectDetails(projectName: String) {
        currentProjectDetails = projectRepository.getProjectDetails("rodrixan", projectName)
    }


}