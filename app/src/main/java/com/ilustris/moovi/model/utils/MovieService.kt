package com.ilustris.moovify.utils

import com.ilustris.moovify.utils.NetworkUtils.retrofitService
import com.ilustris.moovify.utils.network.GetMovieDetails
import retrofit2.Retrofit

class MovieService {

    val path = "/movie/"

    fun getMovie(): GetMovieDetails {
        val retrofit: Retrofit = retrofitService(path)
        return retrofit.create(GetMovieDetails::class.java)
    }

    fun getSimilarMovies(): GetMovieDetails {
        val retrofit: Retrofit = retrofitService(path)
        return retrofit.create(GetMovieDetails::class.java)
    }


}