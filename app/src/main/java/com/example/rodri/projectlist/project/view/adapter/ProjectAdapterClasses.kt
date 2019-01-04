package com.example.rodri.projectlist.project.view.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.util.loadFromDrawable
import com.example.rodri.projectlist.common.view.RecyclerViewBaseAdapter

class ProjectAdapter : RecyclerViewBaseAdapter<ProjectListItem, ProjectListItemView>() {
    override fun bind(
        data: ProjectListItem,
        itemView: ProjectListItemView
    ) {
        with(itemView) {
            this.data = data
            projectName.text = data.name
            projectLanguage.text = context.getString(R.string.rv_item_project_language_placeholder, data.language)
            projectWatchers.text =
                    context.getString(R.string.rv_item_project_watchers_placeholder, data.watchersCount)
            projectAvatar.loadFromDrawable(data.language?.getIconByLanguage()?:-1)
            onClickListener = this@ProjectAdapter.onClickListener

        }
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProjectListItemView =
        ProjectListItemView(parent, viewType)
}

fun String.getIconByLanguage() =
    when (this) {
        "Java" -> R.mipmap.ic_java
        "C" -> R.mipmap.ic_c_lang
        "C++" -> R.mipmap.ic_cpp
        "JavaScript" -> R.mipmap.ic_js
        "Python" -> R.mipmap.ic_python
        "Kotlin" -> R.mipmap.ic_kotlin
        else -> -1
    }

class ProjectListItemView(parent: ViewGroup, viewType: Int) : ConstraintLayout(parent.context) {

    private val rootView: ConstraintLayout
    val projectName: TextView
    val projectLanguage: TextView
    val projectWatchers: TextView
    val projectAvatar: ImageView

    var data: ProjectListItem? = null
    var onClickListener: ((ProjectListItem) -> Unit)? = null
        set(value) {
            field = value
            rootView.setOnClickListener { data?.let { onClickListener?.invoke(it) } }
        }


    init {
        inflate(context, R.layout.rv_item_project, this)
        rootView = findViewById(R.id.projectRootView)
        projectName = findViewById(R.id.projectName)
        projectLanguage = findViewById(R.id.projectLanguage)
        projectWatchers = findViewById(R.id.projectWatchers)
        projectAvatar = findViewById(R.id.projectAvatar)
    }
}

