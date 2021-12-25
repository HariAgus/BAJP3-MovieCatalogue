package com.hariagus.finalproject.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val NEWEST = "newest"
    const val OLDEST = "oldest"
    const val POPULARITY = "popularity"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities where isTvShow = 0 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY releaseDate ASC")
            }
            POPULARITY -> {
                simpleQuery.append("ORDER BY popularity DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShow(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities where isTvShow = 1 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY releaseDate ASC")
            }
            POPULARITY -> {
                simpleQuery.append("ORDER BY popularity DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}