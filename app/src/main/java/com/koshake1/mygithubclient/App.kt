package com.koshake1.mygithubclient

import android.app.Application
import com.koshake1.mygithubclient.di.AppComponent
import com.koshake1.mygithubclient.di.DaggerAppComponent
import com.koshake1.mygithubclient.di.modules.AppModule
import com.koshake1.mygithubclient.di.repository.RepositorySubComponent
import com.koshake1.mygithubclient.di.user.UserSubComponent

class App : Application() {
    lateinit var appComponent: AppComponent
        private set
    var userSubComponent: UserSubComponent? = null
        private set
    var repositorySubComponent: RepositorySubComponent? = null
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubComponent() = appComponent.userSubComponent().also { userSubComponent = it }

    fun initRepositorySubComponent() = userSubComponent?.repositorySubComponent().also {
        repositorySubComponent = it
    }

    fun releaseUserSubComponent() {
        userSubComponent = null
    }

    fun releaseRepositorySubComponent() {
        repositorySubComponent = null
    }
}