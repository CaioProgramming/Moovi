package com.ilustris.moovi.model

import android.util.Log
import com.ilustris.moovi.model.contract.MvpContract
import com.ilustris.moovify.model.Movie
import com.ilustris.moovify.utils.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieModel(val presenterImpl: MvpContract.PresenterImpl) : MvpContract.ModelImpl {


    override fun loadMovie() {
        val movieID = 299534
        val call = MovieService().getMovie().movie(movieID.toString())
        Log.d(this.javaClass.simpleName, "Making call...")
        call.enqueue(object : Callback<Movie?> {
            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                Log.e("Callback fail", t.message!!)
            }

            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                Log.i("Callback", "response ${response.message()}")
                Log.d("Callback", "called ${response.raw().request().url()}")

                response.body()?.let {
                    presenterImpl.getMovie(it)
                }
            }

        })
    }

    override fun loadSimilarMovies(id: Int) {
        val call = MovieService().getSimilarMovies().similarMovies(id.toString())
        call.enqueue(object : Callback<SimilarReturn> {
            override fun onFailure(call: Call<SimilarReturn>, t: Throwable) {
                Log.e("Similar Callback fail", t.message!!)
            }

            override fun onResponse(call: Call<SimilarReturn>, response: Response<SimilarReturn>) {
                Log.i("Similar movies Callback", "response ${response.message()}")
                Log.d("Similar movies Callback", "called ${response.raw().request().url()}")

                response.body()?.let {
                    Log.d("Similar movies Callback", "loaded ${it.results.size} movies")
                    presenterImpl.getSimilarMovies(it.results)
                }
            }

        })
    }
}