package com.example.myapplication.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.source.viewmodel.MovieViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class MovieVMFModule {

    @Singleton
    @Binds
    abstract fun getMovieVMF(movieViewModelFactory: MovieViewModelFactory) : ViewModelProvider.Factory
//
//    @Singleton
//    @Binds
//    abstract fun getNetworkDSF(networkDataSourceFactory: NetworkDataSourceFactory) : DataSource.Factory<Int, Movie>
//
//    @Singleton
//    @Binds
//    abstract fun getDbDSF(dbDataSourceFactory: DbDataSourceFactory) : DataSource.Factory<Int,Movie>
}