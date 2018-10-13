package com.example.rodri.projectlist.project.viewmodel

import androidx.lifecycle.LiveData
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.LiveDataWrapper

interface ProjectListViewModel {
    fun getObservableProjectList(): LiveData<LiveDataWrapper<List<ProjectListItem>>>
}