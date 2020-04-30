package com.example.myapplication.source.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.myapplication.model.Movie
import com.example.myapplication.source.MovieRepository
import javax.inject.Inject

class MovieViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {
    fun getData() : MediatorLiveData<PagedList<Movie>> {
        return movieRepository.mediatorLiveData
    }
}