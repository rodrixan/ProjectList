package com.example.rodri.projectlist.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.project.view.ProjectListFragment


class ProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)

        if (savedInstanceState == null) {
            val fragment = ProjectListFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, ProjectListFragment.fragmentTag).commit()
        }
    }
}
