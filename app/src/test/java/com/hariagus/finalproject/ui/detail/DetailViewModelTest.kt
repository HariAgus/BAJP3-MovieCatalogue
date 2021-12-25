package com.hariagus.finalproject.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.utils.DataDummyMovies
import com.hariagus.finalproject.utils.DataDummyTvShow
import com.hariagus.finalproject.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    private val dataDummyMovie = DataDummyMovies.generateDummyMovies()[0]
    private val movieId = dataDummyMovie.id
    private val dataDummyTvShow =  DataDummyTvShow.generateDummyTvShow()[0]
    private val tvShowId = dataDummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(movieRepository)
        detailViewModel.setSelectedMovie(movieId)
        detailViewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getDetailMovie() {
        val movieDetail = Resource.success(dataDummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = movieDetail
        `when`(movieRepository.getMovieById(movieId)).thenReturn(movie)
        detailViewModel.movieDetail.observeForever(movieObserver)
        verify(movieObserver).onChanged(movieDetail)
    }

    @Test
    fun getDetailTvShow() {
        val tvShowDetail = Resource.success(dataDummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = tvShowDetail
        `when`(movieRepository.getTvShowById(tvShowId)).thenReturn(movie)
        detailViewModel.tvShowDetail.observeForever(movieObserver)
        verify(movieObserver).onChanged(tvShowDetail)
    }

}