package com.koshake1.mygithubclient

import android.app.Application
import com.koshake1.mygithubclient.di.AppComponent
import com.koshake1.mygithubclient.di.DaggerAppComponent
import com.koshake1.mygithubclient.di.modules.AppModule

class App : Application() {
    lateinit var appComponent: AppComponent
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
}