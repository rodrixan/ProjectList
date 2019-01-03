package com.example.rodri.projectlist.project.view.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.rest.model.ProjectListItem
import com.example.rodri.projectlist.common.view.RecyclerViewBaseAdapter

class ProjectAdapter : RecyclerViewBaseAdapter<ProjectListItem, ProjectListItemView>() {

    override fun bind(data: ProjectListItem, view: ProjectListItemView) {
        view.projectName.text = data.name
        view.projectLanguage.text = view.context.getString(R.string.rv_item_project_language_placeholder, data.language)
        view.projectWatchers.text =
                view.context.getString(R.string.rv_item_project_watchers_placeholder, data.watchersCount)
        view.projectAvatar.loadDrawable(getIconByLanguage(data.language))
    }

    fun getIconByLanguage(lang: String?) =
        when (lang) {
            "Java" -> R.mipmap.ic_java
            "C" -> R.mipmap.ic_c_lang
            "C++" -> R.mipmap.ic_cpp
            "JavaScript" -> R.mipmap.ic_js
            "Python" -> R.mipmap.ic_python
            null -> -1
            else -> -1
        }


    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProjectListItemView =
        ProjectListItemView(parent, viewType)
}

class ProjectListItemView(parent: ViewGroup, viewType: Int) : ConstraintLayout(parent.context) {

    var projectName: TextView
    var projectLanguage: TextView
    var projectWatchers: TextView
    var projectAvatar: ImageView

    init {
        inflate(context, R.layout.rv_item_project, this)

        projectName = findViewById(R.id.projectName)
        projectLanguage = findViewById(R.id.projectLanguage)
        projectWatchers = findViewById(R.id.projectWatchers)
        projectAvatar = findViewById(R.id.projectAvatar)

    }
}

fun ImageView.loadDrawable(drawableId:Int){
    if(drawableId!=-1) {
        Glide.with(context.applicationContext).load(drawableId).apply(RequestOptions().fitCenter()).into(this)
    }
}