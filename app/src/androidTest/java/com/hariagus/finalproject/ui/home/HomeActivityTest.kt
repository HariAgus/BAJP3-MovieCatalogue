package com.hariagus.finalproject.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
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
        onView(withId(R.id.ivChangeLanguage)).perform(click())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size)
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.posterBg)).check(matches(isDisplayed()))
        onView(withId(R.id.roundedPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPopularity)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size)
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.posterBg)).check(matches(isDisplayed()))
        onView(withId(R.id.roundedPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPopularity)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.fabFavorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.ivFavoriteActivity)).perform(click())
        onView(withId(R.id.rvMovieFavorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.posterBg)).check(matches(isDisplayed()))
        onView(withId(R.id.roundedPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.fabFavorite)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadFavoriteTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.fabFavorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.ivFavoriteActivity)).perform(click())
        onView(withText("Favorite TV")).perform(click())
        onView(withId(R.id.rvTvShowFavorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.posterBg)).check(matches(isDisplayed()))
        onView(withId(R.id.roundedPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.fabFavorite)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

}