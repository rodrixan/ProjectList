package com.example.rodri.projectlist.repository

import android.arch.lifecycle.LiveData
import com.example.rodri.projectlist.rest.model.ProjectListItem

interface ProjectRepository {
    fun getProjectList(userId:String): LiveData<List<ProjectListItem>>
}