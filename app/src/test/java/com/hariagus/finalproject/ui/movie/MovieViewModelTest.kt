package com.hariagus.finalproject.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.utils.SortUtils
import com.hariagus.finalproject.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private val sort = SortUtils.NEWEST

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dataDummyMovie = Resource.success(pagedList)
        `when`(dataDummyMovie.data?.size).thenReturn(2)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dataDummyMovie

        `when`(movieRepository.getAllMovies(sort)).thenReturn(movie)
        val movieEntities = movieViewModel.getMovies(sort).value?.data
        verify(movieRepository).getAllMovies(sort)
        assertNotNull(movieEntities)
        assertEquals(2, movieEntities?.size)

        movieViewModel.getMovies(sort).observeForever(observer)
        verify(observer).onChanged(dataDummyMovie)
    }

}