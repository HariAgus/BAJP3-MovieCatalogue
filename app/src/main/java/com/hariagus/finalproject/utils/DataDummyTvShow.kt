package com.hariagus.finalproject.utils

import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.data.source.remote.response.TvShowItem

object DataDummyTvShow {

    fun generateDummyTvShow(): List<MovieEntity> {
        val tvShow = ArrayList<MovieEntity>()
        tvShow.add(
            MovieEntity(
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "en",
                "Cobra Kai",
                1495.092,
                8.1,
                77169,
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                "2018-05-02",
                1529,
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
            )
        )
        tvShow.add(
            MovieEntity(
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "en",
                "Chilling Adventures of Sabrina",
                1097.927,
                8.4,
                79242,
                "/8AdmUPTyidDebwIuakqkSt6u1II.jpg",
                "2018-10-26",
                1988,
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
            )
        )
        return tvShow
    }

    fun generateRemoteDummyTvShow(): List<TvShowItem> {
        val tvShow = ArrayList<TvShowItem>()
        tvShow.add(
            TvShowItem(

                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "en",
                "Cobra Kai",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                "2018-05-02",
                1495.092,
                8.1,
                77169,
                1529,
            )
        )
        tvShow.add(
            TvShowItem(
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "en",
                "Chilling Adventures of Sabrina",
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
                "/8AdmUPTyidDebwIuakqkSt6u1II.jpg",
                "2018-10-26",
                1097.927,
                8.4,
                79242,
                1988
            )
        )
        return tvShow
    }

}