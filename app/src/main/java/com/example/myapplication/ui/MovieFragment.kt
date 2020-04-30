package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.AppBase
import com.example.myapplication.R
import com.example.myapplication.ui.adapter.ListAdapter
import com.example.myapplication.model.Movie
import com.example.myapplication.source.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */


class MovieFragment : Fragment(R.layout.fragment_movie) {
    companion object {
        private const val TAG = "MovieFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var listAdapter : ListAdapter
    private lateinit var listMovie : PagedList<Movie>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "Entry Movie Fragment");
        AppBase.appComponent.inject(this)
        movieViewModel = ViewModelProvider(this,
            viewModelFactory
        ).get(MovieViewModel::class.java)

        movieViewModel.getData().observe(this.viewLifecycleOwner, Observer {
            listMovie = it
            initUI()
        })
    }

    fun initUI(){
        listAdapter = ListAdapter(context!!)
        listAdapter.submitList(listMovie)
        rv_listMovie.layoutManager = LinearLayoutManager(this.context)
        rv_listMovie.adapter = listAdapter
    }
}
