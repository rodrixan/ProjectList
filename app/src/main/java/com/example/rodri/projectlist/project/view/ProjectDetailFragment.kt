package com.example.rodri.projectlist.project.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.GlobalConstants
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.fragment.BaseFragment
import com.example.rodri.projectlist.common.rest.model.ProjectDetails
import com.example.rodri.projectlist.common.util.loadFromDrawable
import com.example.rodri.projectlist.common.util.loadFromUrl
import com.example.rodri.projectlist.project.view.adapter.getIconByLanguage
import com.example.rodri.projectlist.project.viewmodel.GithubProjectDetailViewModel
import com.example.rodri.projectlist.project.viewmodel.ProjectDetailViewModel
import kotlinx.android.synthetic.main.fragment_project_detail.*
import org.jetbrains.anko.support.v4.ctx
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class ProjectDetailFragment : BaseFragment() {

    private val viewModel: ProjectDetailViewModel by viewModel<GithubProjectDetailViewModel>()
    private val projectDetailsObserver = Observer<AppInternalData<ProjectDetails>> {
        when (it) {
            null, is AppInternalData.Loading -> {
                ivProjectOwnerAvatar.loadFromUrl(getString(R.string.loading_gif_url))
            }
            is AppInternalData.Error -> {
                ivProjectOwnerAvatar.loadFromDrawable(R.drawable.ic_img_placeholder)
                tvProjectName.apply {
                    text = it.message
                    setTextColor(ctx.getColor(R.color.colorError))
                }
            }
            is AppInternalData.Success -> {
                loadData(it.data)
            }
        }
    }

    private fun loadData(details: ProjectDetails) {
        with(details) {
            Timber.d("Created at: ${creationDate}")
            Timber.d("Updated at: ${lastUpdateDate}")
            language?.getIconByLanguage()?.let {
                ivProjectOwnerAvatar.loadFromDrawable(it)
            } ?: ivProjectOwnerAvatar.loadFromUrl(owner?.avatarUrl, Pair(200, 200))
            tvProjectOwnerName.text = owner?.name
            tvProjectName.text = name
            tvProjectLanguage.text = getString(R.string.rv_item_project_language_placeholder, language)
            tvProjectWatchers.text = getString(R.string.rv_item_project_watchers_placeholder, watchersCount)
            tvProjectIssues.text = getString(R.string.project_detail_issues_placeholder, openIssuesCount)
            tvProjectCreationDate.text =
                    getString(R.string.project_detail_create_date_placeholder, creationDate?.parseDDMMYYYDate() ?: "")
            tvProjectUpdateDate.text =
                    getString(R.string.project_detail_update_date_placeholder, lastUpdateDate?.parseDDMMYYYDate() ?: "")
            tvProjectCloneUrl.text = getString(R.string.project_detail_clone_url_placeholder, cloneUrl)
        }
    }

    override fun getFragmentTag() = FRAGMENT_TAG

    private var projectName: String = ""


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getString(ARG_PROJECT_NAME_KEY)?.let {
            projectName = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.currentProjectDetails.observe(this, projectDetailsObserver)
        if (savedInstanceState == null) {
            //only load first time
            viewModel.loadProjectDetails(projectName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_project_detail, container, false)

    override fun onDestroy() {
        super.onDestroy()
        viewModel.currentProjectDetails.removeObserver(projectDetailsObserver)
    }

    companion object {
        fun newInstance(projectName: String) = ProjectDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PROJECT_NAME_KEY, projectName)
            }
        }

        const val ARG_PROJECT_NAME_KEY = "ProjectName"
        val FRAGMENT_TAG = "${this.javaClass.simpleName}"
    }
}

fun Date.parseDDMMYYYDate(): String {
    val sym = DateFormatSymbols.getInstance()
    return SimpleDateFormat(GlobalConstants.DATE_FORMAT_DATA, sym).format(this)
}