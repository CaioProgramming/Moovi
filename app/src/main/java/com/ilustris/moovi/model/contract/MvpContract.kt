package com.ilustris.moovi.model.contract

import android.view.View
import androidx.viewbinding.ViewBinding
import com.ilustris.moovi.model.Genre
import com.ilustris.moovify.model.Movie

interface MvpContract {
    interface ModelImpl {
        fun loadMovie()
        fun loadSimilarMovies(id: Int)
    }


    interface PresenterImpl {
        val view: View
        fun getSimilarMovies(movies: List<Movie>)
        fun getMovie(movie: Movie)
    }

    interface ViewImpl {
        fun viewBinding(): ViewBinding
        fun showLoad(load: Boolean)
        fun showMovieData(movie: Movie)
        fun showSimilarMovies(movies: List<Movie>)
    }

    interface AdapterImpl {
        fun movieGenres(genres: List<Genre>)
    }
}