package com.example.myapplication.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule constructor(val application : Application) {

    @Singleton
    @Provides
    fun getApp() : Application {
        return application
    }

    @Singleton
    @Provides
    fun getExecutor() : Executor {
        return Executors.newFixedThreadPool(5)
    }
}