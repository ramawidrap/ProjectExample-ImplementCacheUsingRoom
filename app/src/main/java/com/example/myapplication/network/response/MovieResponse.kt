package com.example.myapplication.network.response

import com.example.myapplication.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page :String,
    @SerializedName("results")
    val results:List<Movie>
)