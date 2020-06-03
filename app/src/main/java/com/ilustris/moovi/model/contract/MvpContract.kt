package com.ilustris.moovi.model.contract

import android.view.View
import androidx.viewbinding.ViewBinding
import com.ilustris.moovify.model.Movie

interface MvpContract {
    interface ModelImpl {
        fun loadMovie(id: Int, findSimilarMovies: Boolean)
        fun loadSimilarMovies(id: Int)
    }


    interface PresenterImpl {
        val view: View
        fun getSimilarMovies(movies: List<Movie>)
        fun getMovieData(movie: Movie)
    }

    interface ViewImpl {
        fun viewBinding(): ViewBinding
        fun showLoad(load: Boolean)
        fun showMovieData(movie: Movie)
        fun showSimilarMovies(movies: List<Movie>)
    }

}