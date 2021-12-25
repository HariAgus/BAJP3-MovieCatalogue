package com.hariagus.finalproject.data.source


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hariagus.finalproject.utils.PagedListUtils
import com.hariagus.finalproject.data.source.local.LocalDataSource
import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.data.source.remote.RemoteDataSource
import com.hariagus.finalproject.utils.*
import com.hariagus.finalproject.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val sort = SortUtils.NEWEST

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)
    private val movieResponse = DataDummyMovies.generateRemoteDummyMovies()
    private val movieId = movieResponse[0].id
    private val tvShowResponse = DataDummyTvShow.generateRemoteDummyTvShow()
    private val tvShowId = tvShowResponse[0].id

    @Test
    fun getAllMovies() {
        val dummyMovies = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies(sort)).thenReturn(dummyMovies)
        movieRepository.getAllMovies(sort)

        val moviesEntities = Resource.success(PagedListUtils.mockPagedList(DataDummyMovies.generateDummyMovies()))
        verify(local).getAllMovies(sort)
        assertNotNull(moviesEntities.data)
        assertEquals(movieResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieById() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummyMovies.generateDummyMovies()[0]
        `when`(local.getMovie(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId)).data
        verify(local).getMovie(movieId)
        val movieResponse = movieResponse[0]
        assertNotNull(movieEntity)
        if (movieEntity != null) {
            assertEquals(movieResponse.overview, movieEntity.overview)
            assertEquals(movieResponse.originalLanguage, movieEntity.originalLanguage)
            assertEquals(movieResponse.title, movieEntity.title)
            assertEquals(movieResponse.posterPath, movieEntity.posterPath)
            assertEquals(movieResponse.backdropPath, movieEntity.backdropPath)
            assertEquals(movieResponse.releaseDate, movieEntity.releaseDate)
            assertEquals(movieResponse.popularity, movieEntity.popularity)
            assertEquals(movieResponse.voteAverage, movieEntity.voteAverage)
            assertEquals(movieResponse.id, movieEntity.id)
            assertEquals(movieResponse.voteCount, movieEntity.voteCount)
        }
    }

    @Test
    fun getAllTvShow() {
        val dummyTvShow = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllTvShows(sort)).thenReturn(dummyTvShow)
        movieRepository.getAllTvShow(sort)

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummyTvShow.generateDummyTvShow()))
        verify(local).getAllTvShows(sort)
        assertNotNull(tvShowEntities.data)
        assertEquals(movieResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowId() {
        val dummyTvShow = MutableLiveData<MovieEntity>()
        dummyTvShow.value = DataDummyTvShow.generateDummyTvShow()[0]
        `when`(local.getTvShow(tvShowId)).thenReturn(dummyTvShow)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getTvShowById(tvShowId)).data
        verify(local).getTvShow(tvShowId)
        val tvShowResponse = tvShowResponse[0]
        assertNotNull(movieEntity)
        if (movieEntity != null) {
            assertEquals(tvShowResponse.overview, movieEntity.overview)
            assertEquals(tvShowResponse.originalLanguage, movieEntity.originalLanguage)
            assertEquals(tvShowResponse.name, movieEntity.title)
            assertEquals(tvShowResponse.popularity, movieEntity.popularity)
            assertEquals(tvShowResponse.voteAverage, movieEntity.voteAverage)
            assertEquals(tvShowResponse.id, movieEntity.id)
            assertEquals(tvShowResponse.backdropPath, movieEntity.backdropPath)
            assertEquals(tvShowResponse.firstAirDate, movieEntity.releaseDate)
            assertEquals(tvShowResponse.voteCount, movieEntity.voteCount)
            assertEquals(tvShowResponse.posterPath, movieEntity.posterPath)
        }
    }

    @Test
    fun getFavoriteMovies() {
        val dataFavoriteMovies = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllFavoriteMovies()).thenReturn(dataFavoriteMovies)
        movieRepository.getFavoriteMovies()

        val favoriteMovieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummyMovies.generateDummyMovies()))
        verify(local).getAllFavoriteMovies()
        assertNotNull(favoriteMovieEntities.data)
        assertEquals(movieResponse.size.toLong(), favoriteMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dataFavoriteTvShow = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllFavoriteTvShow()).thenReturn(dataFavoriteTvShow)
        movieRepository.getFavoriteTvShow()

        val favoriteTvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummyTvShow.generateDummyTvShow()))
        verify(local).getAllFavoriteTvShow()
        assertNotNull(favoriteTvShowEntities.data)
        assertEquals(movieResponse.size.toLong(), favoriteTvShowEntities.data?.size?.toLong())
    }

}