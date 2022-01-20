package com.hariagus.finalproject.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickBack
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.hariagus.finalproject.R
import com.hariagus.finalproject.utils.DataDummyMovies
import com.hariagus.finalproject.utils.DataDummyTvShow
import com.hariagus.finalproject.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeActivityTest() {

    private val dummyMovies = DataDummyMovies.generateDummyMovies()
    private val dummyTvShows = DataDummyTvShow.generateDummyTvShow()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun clickChangeLanguage() {
        clickOn(R.id.ivChangeLanguage)
    }

    @Test
    fun loadMovies() {
        assertDisplayed(R.id.rvMovie)
        clickListItem(R.id.rvMovie, dummyMovies.size)
    }

    @Test
    fun loadDetailMovies() {
        clickListItem(R.id.rvMovie, 0)
        assertDisplayed(R.id.posterBg)
        assertDisplayed(R.id.roundedPosterDetail)
        assertDisplayed(R.id.tvTitleDetail)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvOverview)
        assertDisplayed(R.id.tvLanguage)
        assertDisplayed(R.id.tvPopularity)
    }

    @Test
    fun loadTvShow() {
        clickOn("TV SHOW")
        assertDisplayed(R.id.rvTvShow)
        clickListItem(R.id.rvTvShow, dummyTvShows.size)
    }

    @Test
    fun loadDetailTvShow() {
        clickOn("TV SHOW")
        clickListItem(R.id.rvTvShow, 0)
        assertDisplayed(R.id.roundedPosterDetail)
        assertDisplayed(R.id.tvTitleDetail)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvOverview)
        assertDisplayed(R.id.tvLanguage)
        assertDisplayed(R.id.tvPopularity)
    }

    @Test
    fun loadFavoriteMovies() {
        clickListItem(R.id.rvMovie, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        clickOn(R.id.ivFavoriteActivity)
        clickListItem(R.id.rvMovieFavorite, 0)

        assertDisplayed(R.id.roundedPosterDetail)
        assertDisplayed(R.id.tvTitleDetail)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvOverview)
        assertDisplayed(R.id.tvLanguage)
        assertDisplayed(R.id.tvPopularity)

        clickOn(R.id.fabFavorite)
        clickBack()
    }

    @Test
    fun loadFavoriteTvShow() {
        clickOn("TV SHOW")
        clickListItem(R.id.rvTvShow, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        clickOn(R.id.ivFavoriteActivity)
        clickOn("Favorite TV")
        clickListItem(R.id.rvTvShowFavorite, 0)

        assertDisplayed(R.id.roundedPosterDetail)
        assertDisplayed(R.id.tvTitleDetail)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvOverview)
        assertDisplayed(R.id.tvLanguage)
        assertDisplayed(R.id.tvPopularity)

        clickOn(R.id.fabFavorite)
        clickBack()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

}