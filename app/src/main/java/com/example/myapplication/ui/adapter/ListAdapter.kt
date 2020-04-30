package com.example.myapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Movie
import kotlinx.android.synthetic.main.list_movie.view.*

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"


class ListAdapter(private val context : Context) :  PagedListAdapter<Movie, ListAdapter.ListViewHolder>(Movie.CALLBACK){

    class ListViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val movieImage = itemView.imageMovie_Iv
        val title = itemView.titleMovie_Tv
        val releaseDate = itemView.releaseMovie_Tv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.list_movie,parent,false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.let { holder.title.text = getItem(position)?.title}
        holder.releaseDate.text = getItem(position)!!.releaseDate
        val url = BASE_IMAGE_URL + getItem(position)!!.posterPath
        Glide.with(context).load(url).placeholder(R.drawable.placeholder).into(holder.movieImage)

    }
}