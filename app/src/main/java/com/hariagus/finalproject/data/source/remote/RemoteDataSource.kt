package com.hariagus.finalproject.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hariagus.finalproject.data.source.remote.response.MovieItem
import com.hariagus.finalproject.data.source.remote.response.MovieResponse
import com.hariagus.finalproject.data.source.remote.response.TvShowItem
import com.hariagus.finalproject.data.source.remote.response.TvShowResponse
import com.hariagus.finalproject.network.NetworkClient
import com.hariagus.finalproject.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private val TAG: String = RemoteDataSource::class.java.simpleName

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieItem>>>()
        NetworkClient.getApiService().getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val result = response.body()?.results
                if (result != null) {
                    resultMovie.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovie
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShowItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowItem>>>()
        NetworkClient.getApiService().getTvShow().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                val result = response.body()?.results
                if (result != null) {
                    resultTvShow.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}", )
                EspressoIdlingResource.decrement()
            }
        })
        return  resultTvShow
    }

}