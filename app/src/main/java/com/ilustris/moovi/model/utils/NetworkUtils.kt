package com.ilustris.moovify.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {

    val postersPath = "https://image.tmdb.org/t/p/w1280/"
    val baseURL = "https://api.themoviedb.org/3"
    fun retrofitService(url: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseURL + url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


}