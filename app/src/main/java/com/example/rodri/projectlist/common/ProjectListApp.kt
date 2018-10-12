package com.example.rodri.projectlist.common

import android.app.Application
import com.example.rodri.projectlist.common.util.DelegatesExtensions
import timber.log.Timber

class ProjectListApp : Application() {
    companion object {
        var instance: ProjectListApp by DelegatesExtensions.notNullSingleValue()
            private set
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }
}