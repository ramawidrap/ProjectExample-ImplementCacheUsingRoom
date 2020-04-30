package com.example.myapplication.di.module

import com.example.myapplication.network.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getService(retrofit: Retrofit) : MovieService {
        return retrofit.create(MovieService::class.java)
    }
}