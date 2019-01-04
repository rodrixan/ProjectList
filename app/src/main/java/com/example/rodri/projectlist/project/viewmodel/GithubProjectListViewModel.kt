package com.example.rodri.projectlist.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.project.repository.ProjectRepository

class GithubProjectListViewModel(private val projectRepository: ProjectRepository) : ViewModel(), ProjectListViewModel {

    override val projectList: LiveData<AppInternalData<List<ProjectListItem>>> =
        projectRepository.getProjectList("rodrixan")

}