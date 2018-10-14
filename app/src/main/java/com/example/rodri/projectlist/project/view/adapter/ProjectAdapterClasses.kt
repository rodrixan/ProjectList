package com.example.rodri.projectlist.project.view.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.view.RecyclerViewBaseAdapter

class ProjectAdapter : RecyclerViewBaseAdapter<ProjectListItem, ProjectListItemView>() {
    override fun bind(data: ProjectListItem, view: ProjectListItemView) {
        view.projectName.text = data.name
        view.projectLanguage.text = view.context.getString(R.string.rv_item_project_language_placeholder, data.language)
        view.projectWatchers.text =
                view.context.getString(R.string.rv_item_project_watchers_placeholder, data.watchersCount)
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProjectListItemView =
        ProjectListItemView(parent, viewType)
}

class ProjectListItemView(parent: ViewGroup, viewType: Int) : ConstraintLayout(parent.context) {

    var projectName: TextView
    var projectLanguage: TextView
    var projectWatchers: TextView

    init {
        inflate(context, R.layout.rv_item_project, this)

        projectName = findViewById(R.id.projectName)
        projectLanguage = findViewById(R.id.projectLanguage)
        projectWatchers = findViewById(R.id.projectWatchers)
    }
}