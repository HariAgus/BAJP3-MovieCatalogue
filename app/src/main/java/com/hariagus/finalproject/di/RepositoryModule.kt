package com.hariagus.finalproject.di

import com.hariagus.finalproject.data.source.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get(), get(), get()) }
}