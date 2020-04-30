package com.example.myapplication.di.module

import android.app.Application
import androidx.room.Room
import com.example.myapplication.local.AppDatabase
import com.example.myapplication.local.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application.applicationContext,AppDatabase::class.java,"MovieDB").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase) : MovieDao {
        return appDatabase.getMovieDao()
    }
}