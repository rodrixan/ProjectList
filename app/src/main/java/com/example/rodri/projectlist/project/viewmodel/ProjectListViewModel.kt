package com.example.rodri.projectlist.project.viewmodel

import android.arch.lifecycle.LiveData
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.LiveDataWrapper

interface ProjectListViewModel {
    fun getObservableProjectList(): LiveData<LiveDataWrapper<List<ProjectListItem>>>
}