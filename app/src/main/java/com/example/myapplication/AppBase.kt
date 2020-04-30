package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.component.DaggerMainComponent
import com.example.myapplication.di.component.MainComponent
import com.example.myapplication.di.module.AppModule
import com.example.myapplication.di.module.LocalModule
import com.example.myapplication.di.module.NetworkModule

class AppBase : Application() {

    lateinit var app : AppBase

    companion object {
        lateinit var appComponent : MainComponent
    }
    override fun onCreate() {
        super.onCreate()
        app = this
        appComponent = DaggerMainComponent.builder().appModule(AppModule(this)).localModule(
            LocalModule()
        ).networkModule(NetworkModule()).build()
    }
}