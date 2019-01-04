package com.example.rodri.projectlist.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.rodri.projectlist.R
import com.example.rodri.projectlist.common.fragment.BaseFragment
import com.example.rodri.projectlist.project.view.ProjectListFragment


class ProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)

        if (savedInstanceState == null) {
            goToFragment(ProjectListFragment.newInstance(), addToBackStack = false)
        }
    }


}

fun FragmentActivity.goToFragment(
    fragment: BaseFragment,
    containerId: Int = R.id.main_container,
    addToBackStack: Boolean = true
) {
    val transaction = this.supportFragmentManager.beginTransaction()
    if (addToBackStack) {
        transaction.addToBackStack(fragment.getFragmentTag())
    }
    transaction.replace(containerId, fragment, fragment.getFragmentTag()).commit()
}
