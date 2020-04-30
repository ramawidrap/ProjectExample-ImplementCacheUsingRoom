package com.example.myapplication.di.component

import com.example.myapplication.di.module.AppModule
import com.example.myapplication.di.module.LocalModule
import com.example.myapplication.di.module.MovieVMFModule
import com.example.myapplication.di.module.NetworkModule
import com.example.myapplication.ui.MovieFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,LocalModule::class,NetworkModule::class,MovieVMFModule::class])
interface MainComponent {
    fun inject(movieFragment: MovieFragment)
}