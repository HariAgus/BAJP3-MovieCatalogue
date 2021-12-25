package com.hariagus.finalproject.di

import android.content.Context
import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.data.source.local.LocalDataSource
import com.hariagus.finalproject.data.source.local.room.MovieDatabase
import com.hariagus.finalproject.data.source.remote.RemoteDataSource
import com.hariagus.finalproject.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MovieRepository {

        val database = MovieDatabase.getInstance(context)
        val remoteRepository = RemoteDataSource.getInstance()
        val localDataSource =  LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteRepository, localDataSource, appExecutors)
    }
}