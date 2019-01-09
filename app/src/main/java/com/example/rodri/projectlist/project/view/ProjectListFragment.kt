package com.example.rodri.projectlist.project.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.fragment.BaseFragment
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.util.loadFromUrl
import com.example.rodri.projectlist.project.goToFragment
import com.example.rodri.projectlist.project.view.adapter.ProjectAdapter
import com.example.rodri.projectlist.project.viewmodel.GithubProjectListViewModel
import com.example.rodri.projectlist.project.viewmodel.ProjectListViewModel
import kotlinx.android.synthetic.main.fragment_project_list.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import org.koin.android.viewmodel.ext.android.viewModel


class ProjectListFragment : BaseFragment() {

    private val viewModel: ProjectListViewModel by viewModel<GithubProjectListViewModel>()
    private val projectAdapter: ProjectAdapter = ProjectAdapter().apply {
        onClickListener = { project -> act.goToFragment(ProjectDetailFragment.newInstance(project.name)) }
    }

    override fun getFragmentTag() = FRAGMENT_TAG

    private val projectListObserver = Observer<AppInternalData<List<ProjectListItem>>> {
        when (it) {
            null, is AppInternalData.Loading -> {
                ivLoading.loadFromUrl(getString(R.string.loading_gif_url))
                ivLoading.visibility = View.VISIBLE
            }
            is AppInternalData.Error -> {
                ivLoading.visibility = View.GONE
                toast(it.message)
            }
            is AppInternalData.Success -> {
                ivLoading.visibility = View.GONE
                toast("Received ${it.data.size} projects")
                projectAdapter.updateItems(it.data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.projectList
            .observe(this, projectListObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_project_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvProjects.layoutManager = LinearLayoutManager(act, RecyclerView.VERTICAL, false)
        rvProjects.adapter = projectAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.projectList.removeObserver(projectListObserver)
    }

    companion object {
        fun newInstance() = ProjectListFragment()
        val FRAGMENT_TAG = "${ProjectListFragment::class.simpleName}"
    }
}
