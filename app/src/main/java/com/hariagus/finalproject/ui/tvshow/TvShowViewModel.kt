package com.hariagus.finalproject.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getTvShow(sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getAllTvShow(sort)

}