package com.example.myapplication.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.Movie
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movie : Movie) : Completable

    @Query("select * from movies")
    fun getAll() : DataSource.Factory<Int,Movie>


}