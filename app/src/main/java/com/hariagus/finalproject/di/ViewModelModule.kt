package com.hariagus.finalproject.di

import com.hariagus.finalproject.ui.detail.DetailViewModel
import com.hariagus.finalproject.ui.favorite.FavoriteViewModel
import com.hariagus.finalproject.ui.movie.MovieViewModel
import com.hariagus.finalproject.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}