package com.example.rodri.projectlist.common

import android.app.Application
import com.example.rodri.projectlist.common.di.projectModule
import com.example.rodri.projectlist.common.util.DelegatesExtensions
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class ProjectListApp : Application() {
    companion object {
        var instance: ProjectListApp by DelegatesExtensions.notNullSingleValue()
            private set
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        initKoin()
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        startKoin(instance, listOf(projectModule))
    }
}