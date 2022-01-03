package com.hariagus.finalproject.di

import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    single { AppExecutors() }
    single { MovieRepository(get(), get(), get()) }
}