package com.example.myapplication.source

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.myapplication.model.Movie
import com.example.myapplication.network.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject



class NetworkMovieSource @Inject constructor(private val movieService: MovieService) : PageKeyedDataSource<Int,Movie>() {

    companion object {
        val API_KEY = "56881c78ed5ff32bc2d49d3a1da127cd"
    }

    val networkStatus = MutableLiveData<Status>()
    val getMovies = PublishSubject.create<Movie>()

    sealed class Status() {
        object Loading : Status()
        data class Success(val listMovie : List<Movie>) : Status()
        data class Error(val message : String) : Status()
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkStatus.postValue(Status.Loading)
        movieService.getMovies(API_KEY,1).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe{
            callback.onResult(it.results,null,2)

            it.results.forEach { movie ->
                Log.i("NetworkMovieSource",movie.toString())
                getMovies.onNext(movie)
            }
            networkStatus.postValue(Status.Success(it.results))
            Consumer<Throwable>{
                Log.i("NetworkMovieSource",it.toString())

                networkStatus.postValue(Status.Error(it.message!!))
            }
        }

    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkStatus.postValue(Status.Loading)
        movieService.getMovies(API_KEY,params.key).observeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).subscribe{
            callback.onResult(it.results,params.key+1)
            networkStatus.postValue(Status.Success(it.results))
            it.results.forEach { movie ->
                Log.i("NetworkMovieSource",movie.toString())
                getMovies.onNext(movie)
            }
        }
        Consumer<Throwable> {
            networkStatus.postValue(Status.Error(it.message!!))
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

}
