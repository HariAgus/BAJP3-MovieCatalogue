package com.hariagus.finalproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val movieId = MutableLiveData<Int>()
    private val tvShowId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var movieDetail: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { movieId ->
            movieRepository.getMovieById(movieId)
        }

    var tvShowDetail: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(tvShowId) { tvShowId ->
            movieRepository.getTvShowById(tvShowId)
        }

    fun setFavoriteMovie() {
        val movieResource = movieDetail.value?.data
        if (movieResource != null) {
            val newState = !movieResource.isFavorite
            movieRepository.setMovieFavorite(movieResource, newState)
        }
    }

    fun setFavoriteTvShow() {
        val tvShowResource = tvShowDetail.value?.data
        if (tvShowResource != null) {
            val newState = !tvShowResource.isFavorite
            movieRepository.setMovieFavorite(tvShowResource, newState)
        }
    }

}