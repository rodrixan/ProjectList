package com.example.rodri.projectlist.common.di


import com.example.rodri.projectlist.common.rest.service.ProjectApiService
import com.example.rodri.projectlist.project.repository.GithubProjectRepository
import com.example.rodri.projectlist.project.repository.ProjectRepository
import com.example.rodri.projectlist.project.viewmodel.GithubProjectListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel

import org.koin.dsl.module.module

val projectModule = module {
    single { ProjectApiService.getApi() }
    single<ProjectRepository> { GithubProjectRepository(get()) }
    viewModel { GithubProjectListViewModel(get()) }
}