package com.example.rodri.projectlist.project.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rodri.projectlist.common.ProjectListApp

import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.ObserverWrapper
import com.example.rodri.projectlist.project.viewmodel.GithubProjectListViewModel
import com.example.rodri.projectlist.project.viewmodel.ProjectListViewModel
import org.jetbrains.anko.support.v4.toast


class ProjectListFragment : Fragment() {

    private val viewModel: ProjectListViewModel by lazy {
        ViewModelProviders.of(this) .get(GithubProjectListViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getObservableProjectList()
            .observe(this, ObserverWrapper(object : ObserverWrapper.ObserverCallbacks<List<ProjectListItem>> {
                override fun onChange(data: List<ProjectListItem>) {
                    toast("Received ${data.size} projects")
                }

                override fun onError(errorMessage: String?) {
                    toast(errorMessage ?: "Error is null")
                }
            }))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_project_list, container, false)
    }


    companion object {
        fun newInstance() = ProjectListFragment()
    }
}
