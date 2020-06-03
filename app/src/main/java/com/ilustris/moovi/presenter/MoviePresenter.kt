package com.ilustris.moovi.presenter

import android.view.View
import com.ilustris.moovi.model.MovieModel
import com.ilustris.moovi.model.contract.MvpContract
import com.ilustris.moovify.model.Movie

class MoviePresenter(private val viewImpl: MvpContract.ViewImpl) : MvpContract.PresenterImpl {

    override val view: View get() = viewImpl.viewBinding().root
    private val movieModel = MovieModel(this)


    override fun getSimilarMovies(movies: List<Movie>) {
        viewImpl.showSimilarMovies(movies)
    }

    fun findMovie() {
        viewImpl.showLoad(true)
        movieModel.loadMovie()
    }


    override fun getMovie(movie: Movie) {
        viewImpl.showMovieData(movie)
        movieModel.loadSimilarMovies(movie.id)
        viewImpl.showLoad(false)
    }


}