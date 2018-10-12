package com.example.rodri.projectlist.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.rodri.projectlist.ProjectListApp
import com.example.rodri.projectlist.repository.GithubProjectRepository
import com.example.rodri.projectlist.repository.ProjectRepository
import com.example.rodri.projectlist.rest.model.ProjectListItem

class GithubProjectListViewModel : AndroidViewModel(ProjectListApp.instance), ProjectListViewModel {

    private val projectRepository: ProjectRepository by lazy { GithubProjectRepository() }
    private val observableProjectList: LiveData<List<ProjectListItem>>

    init {
        observableProjectList = projectRepository.getProjectList("rodrixan")
    }

    override fun getObservableProjectList(): LiveData<List<ProjectListItem>> = observableProjectList
}