package com.example.rodri.projectlist.project.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.wrapper.ObserverWrapper
import com.example.rodri.projectlist.project.viewmodel.GithubProjectListViewModel
import com.example.rodri.projectlist.project.viewmodel.ProjectListViewModel
import org.jetbrains.anko.support.v4.toast
import org.koin.android.viewmodel.ext.android.viewModel


class ProjectListFragment : Fragment() {

    private val viewModel: ProjectListViewModel by viewModel<GithubProjectListViewModel>()

    private val projectListObserver =
        ObserverWrapper(object : ObserverWrapper.ObserverCallbacks<List<ProjectListItem>> {
            override fun onChange(data: List<ProjectListItem>) {
                toast("Received ${data.size} projects")
            }

            override fun onError(errorMessage: String?) {
                toast(errorMessage ?: "Error is null")
            }
        })

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getObservableProjectList()
            .observe(this, projectListObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_project_list, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getObservableProjectList().removeObserver(projectListObserver)
    }

    companion object {
        fun newInstance() = ProjectListFragment()
    }
}
