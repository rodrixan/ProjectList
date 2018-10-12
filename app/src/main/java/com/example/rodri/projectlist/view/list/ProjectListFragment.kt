package com.example.rodri.projectlist.view.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rodri.projectlist.ProjectListApp

import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.viewmodel.GithubProjectListViewModel
import com.example.rodri.projectlist.viewmodel.ProjectListViewModel
import org.jetbrains.anko.support.v4.toast


class ProjectListFragment : Fragment() {

    private val viewModel: ProjectListViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(ProjectListApp.instance)
            .create(GithubProjectListViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getObservableProjectList().observe(this, Observer {
            it?.let {
            }
        })
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
