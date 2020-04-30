package com.example.myapplication.source.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory @Inject constructor(val movieViewModel: Provider<MovieViewModel>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return movieViewModel.get() as T
    }
}