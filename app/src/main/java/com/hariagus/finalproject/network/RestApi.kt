package com.hariagus.finalproject.network

import com.hariagus.finalproject.BuildConfig
import com.hariagus.finalproject.data.source.remote.response.MovieResponse
import com.hariagus.finalproject.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET("tv")
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowResponse>

}