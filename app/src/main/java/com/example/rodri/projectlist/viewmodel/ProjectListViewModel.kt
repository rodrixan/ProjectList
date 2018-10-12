package com.example.rodri.projectlist.viewmodel

import android.arch.lifecycle.LiveData
import com.example.rodri.projectlist.rest.model.ProjectListItem

interface ProjectListViewModel {
    fun getObservableProjectList(): LiveData<List<ProjectListItem>>
}