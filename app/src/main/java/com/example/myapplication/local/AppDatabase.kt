package com.example.myapplication.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Movie

@Database(entities = [Movie::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao() : MovieDao
}