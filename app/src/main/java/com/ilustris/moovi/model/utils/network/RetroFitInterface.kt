package com.ilustris.moovify.utils.network

import com.ilustris.moovi.model.SimilarReturn
import com.ilustris.moovify.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GetMovieDetails {


    @GET("{movie_id}?api_key=7379f919c04e00b474caee56b9d6cf6a&language=en-US")
    fun movie(@Path("movie_id") movieID: String): Call<Movie>

    @GET("{movie_id}/similar?api_key=7379f919c04e00b474caee56b9d6cf6a&language=en-US")
    fun similarMovies(@Path("movie_id") movieID: String): Call<SimilarReturn>

}