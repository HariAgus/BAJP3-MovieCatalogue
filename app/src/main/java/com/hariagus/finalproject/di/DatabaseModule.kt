package com.hariagus.finalproject.di

import androidx.room.Room
import com.hariagus.finalproject.data.source.local.room.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_db"
        ).fallbackToDestructiveMigration().build()
    }
}