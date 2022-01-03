package com.hariagus.finalproject.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hariagus.finalproject.data.source.MovieRepository
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.utils.SortUtils
import com.hariagus.finalproject.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel
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
        tvShowViewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val dataDummyTvShow = Resource.success(pagedList)
        `when`(dataDummyTvShow.data?.size).thenReturn(2)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dataDummyTvShow

        `when`(movieRepository.getAllTvShow(sort)).thenReturn(movie)
        val movieEntities = tvShowViewModel.getTvShow(sort).value?.data
        verify(movieRepository).getAllTvShow(sort)
        assertNotNull(movieEntities)
        assertEquals(2, movieEntities?.size)

        tvShowViewModel.getTvShow(sort).observeForever(observer)
        verify(observer).onChanged(dataDummyTvShow)
    }

}