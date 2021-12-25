package com.hariagus.finalproject.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.data.source.local.entity.MovieEntity

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getFavoriteMovies()

    fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getFavoriteTvShow()

    fun setFavoriteDataMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavorite
        movieRepository.setMovieFavorite(movieEntity, newState)
    }
}