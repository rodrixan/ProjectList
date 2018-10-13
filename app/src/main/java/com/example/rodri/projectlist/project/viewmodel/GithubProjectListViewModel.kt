package com.example.rodri.projectlist.project.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.rodri.projectlist.project.repository.ProjectRepository
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.LiveDataWrapper

class GithubProjectListViewModel(private val projectRepository: ProjectRepository) : ViewModel(), ProjectListViewModel {

    private val observableProjectList: LiveData<LiveDataWrapper<List<ProjectListItem>>>

    init {
        observableProjectList = projectRepository.getProjectList("rodrixan")
    }

    override fun getObservableProjectList(): LiveData<LiveDataWrapper<List<ProjectListItem>>> = observableProjectList
}