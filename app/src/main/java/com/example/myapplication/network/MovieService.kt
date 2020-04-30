package com.example.myapplication.network

import com.example.myapplication.model.Movie
import com.example.myapplication.network.response.MovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") key : String,@Query("page") page : Int) : Flowable<MovieResponse>
}