package com.hariagus.finalproject.utils

import com.hariagus.finalproject.data.source.local.entity.MovieEntity
import com.hariagus.finalproject.data.source.remote.response.MovieItem

object DataDummyMovies {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "en",
                "Wonder Woman 1984",
                4167.11,
                7.3,
                464052,
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-16",
                2255,
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            )
        )
        movies.add(
            MovieEntity(
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "en",
                "Soul",
                3283.36,
                8.4,
                508442,
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-25",
                2900,
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
            )
        )
        return movies
    }

    fun generateRemoteDummyMovies(): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        movies.add(
            MovieItem(
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "en",
                "Wonder Woman 1984",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-16",
                4167.11,
                7.3,
                464052,
                2255
            )
        )
        movies.add(
            MovieItem(
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "en",
                "Soul",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-25",
                3283.36,
                8.4,
                508442,
                2900,
            )
        )
        return movies
    }


}