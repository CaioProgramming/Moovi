package com.ilustris.moovify.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {

    val defaultconfig = "?api_key=7379f919c04e00b474caee56b9d6cf6a&language=en-US"
    val postersPath = "https://image.tmdb.org/t/p/w1280/"
    val baseURL = "https://api.themoviedb.org/3"
    fun retrofitService(url: String): Retrofit {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


}