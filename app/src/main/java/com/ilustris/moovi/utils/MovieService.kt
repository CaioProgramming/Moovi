package com.ilustris.moovify.utils

import com.ilustris.moovify.utils.NetworkUtils.baseURL
import com.ilustris.moovify.utils.network.GetMovieDetails
import retrofit2.Retrofit

class MovieService {

    val url = baseURL + "/movie/"

    fun getMovie(): GetMovieDetails {
        val retrofit: Retrofit = NetworkUtils.retrofitService(url)
        return retrofit.create(GetMovieDetails::class.java)
    }


}