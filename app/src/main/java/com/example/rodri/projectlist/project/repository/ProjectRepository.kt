package com.example.rodri.projectlist.project.repository

import androidx.lifecycle.LiveData
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.LiveDataWrapper

interface ProjectRepository {
    fun getProjectList(userId:String): LiveData<LiveDataWrapper<List<ProjectListItem>>>
}