package com.example.rodri.projectlist.project.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.rodri.projectlist.common.ProjectListApp
import com.example.rodri.projectlist.project.repository.GithubProjectRepository
import com.example.rodri.projectlist.project.repository.ProjectRepository
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.LiveDataWrapper

class GithubProjectListViewModel : AndroidViewModel(ProjectListApp.instance),
    ProjectListViewModel {

    private val projectRepository: ProjectRepository by lazy { GithubProjectRepository() }
    private val observableProjectList: LiveData<LiveDataWrapper<List<ProjectListItem>>>

    init {
        observableProjectList = projectRepository.getProjectList("rodrixan")
    }

    override fun getObservableProjectList(): LiveData<LiveDataWrapper<List<ProjectListItem>>> = observableProjectList
}