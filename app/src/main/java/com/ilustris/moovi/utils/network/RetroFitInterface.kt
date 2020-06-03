package com.ilustris.moovify.utils.network

import com.ilustris.moovify.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface GetMovieDetails {


    @GET
    fun movie(@Url url: String): Call<Movie>

}