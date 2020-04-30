package com.example.myapplication.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.myapplication.local.AppDatabase
import com.example.myapplication.model.Movie
import com.example.myapplication.network.MovieService
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import javax.inject.Inject

val API_KEY = "56881c78ed5ff32bc2d49d3a1da127cd"


class MovieRepository @Inject constructor(val db: AppDatabase, private val movieService: MovieService,  executor: Executor)  {
   private  var dbMovieLiveData : LiveData<PagedList<Movie>>
    val mediatorLiveData = MediatorLiveData<PagedList<Movie>>()
    var page=1


    init {

        val boundaryCallback = object : PagedList.BoundaryCallback<Movie>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()

                movieService.getMovies(NetworkMovieSource.API_KEY, page).observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io()).flatMapIterable{
                        it.results
                    }.subscribe {
                        db.getMovieDao().insertAll(it).subscribe{
                        }
                }
                page++
            }

            override fun onItemAtEndLoaded(itemAtEnd: Movie) {
                super.onItemAtEndLoaded(itemAtEnd)
                movieService.getMovies(NetworkMovieSource.API_KEY, page).observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io()).flatMapIterable{
                        it.results
                    }.subscribe {
                        Log.i("loadmore", "insert data")
                    db.getMovieDao().insertAll(it).subscribe {
                        Log.i("loadmore", "complete")

                    }
                }
                page++
            }

            override fun onItemAtFrontLoaded(itemAtFront: Movie) {
                super.onItemAtFrontLoaded(itemAtFront)
                movieService.getMovies(NetworkMovieSource.API_KEY, page).observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io()).flatMapIterable{
                        it.results
                    }.subscribe {
                        Log.i("loadmore", "insert data")
                        db.getMovieDao().insertAll(it)
                    }
                page++
            }

        }

        val dbPagedList = PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(10).setPageSize(10).build()
        dbMovieLiveData = LivePagedListBuilder(db.getMovieDao().getAll(),dbPagedList).setFetchExecutor(executor).setBoundaryCallback(boundaryCallback).build()
//
//        networkDataSourceFactory.getMovies().observeOn(Schedulers.io()).subscribe {
//            Log.i("MovieRepository",it.toString())
//            db.getMovieDao().insertAll(movie = it).subscribe {
//                Log.i("MovieRepository","Success Insert Data")
//            }
//        }

//        mediatorLiveData.addSource(networkMovieLiveData) {
//            mediatorLiveData.value = it
//        }
        mediatorLiveData.addSource(dbMovieLiveData) {
            mediatorLiveData.value = it
        }



    }


}