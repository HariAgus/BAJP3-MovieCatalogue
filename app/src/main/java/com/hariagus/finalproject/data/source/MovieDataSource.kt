package com.hariagus.finalproject.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.vo.Resource

interface MovieDataSource {

    fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getAllTvShow(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShowById(tvShowId: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

}