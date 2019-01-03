package com.example.rodri.projectlist.project.viewmodel

import androidx.lifecycle.LiveData
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.model.ProjectListItem

interface ProjectListViewModel {
    fun getObservableProjectList(): LiveData<AppInternalData<List<ProjectListItem>>>
}