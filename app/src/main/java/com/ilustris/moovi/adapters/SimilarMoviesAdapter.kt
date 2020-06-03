package com.ilustris.moovi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.ilustris.moovi.R
import com.ilustris.moovi.databinding.MovieCardBinding
import com.ilustris.moovi.model.contract.MvpContract
import com.ilustris.moovi.model.utils.Utils
import com.ilustris.moovi.presenter.MoviePresenter
import com.ilustris.moovify.model.Movie
import com.ilustris.moovify.utils.NetworkUtils

class SimilarMoviesAdapter(val context: Context, val movies: List<Movie>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val movieCardBinding = DataBindingUtil.inflate<MovieCardBinding>(
            LayoutInflater.from(context),
            R.layout.movie_card, parent, false
        )
        return MovieHolder(movieCardBinding)
    }

    override fun getItemCount(): Int {
        return if (movies != null) return movies.size else 4
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieBind: MovieCardBinding = (holder as MovieHolder).movieCardBinding
        val movie = movies?.get(position)
        movie?.let {
            movieBind.movie = movie
            movieBind.movieDetails.text =
                "${Utils.convertDate(movie.release_date)} ${getmovieGenres(movie, movieBind)}"

            Glide.with(context).load(NetworkUtils.postersPath + movie.poster_path)
                .into(movieBind.moviePicture)
        }
        val inAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
        movieBind.movieCard.startAnimation(inAnimation)


    }


    private fun getmovieGenres(movie: Movie, movieCardBinding: MovieCardBinding): String {
        var genres = ""
        MoviePresenter(object : MvpContract.ViewImpl {
            override fun viewBinding(): ViewBinding {
                return movieCardBinding
            }

            override fun showLoad(load: Boolean) {
                if (load) movieCardBinding.topshimmer.startShimmer() else movieCardBinding.topshimmer.stopShimmer()
            }

            override fun showMovieData(movie: Movie) {
                TODO("Not yet implemented")
            }

            override fun showSimilarMovies(movies: List<Movie>) {
                TODO("Not yet implemented")
            }
        })
        movie.genres.let {
            for (i in 0..movie.genres.size) {
                val g = movie.genres[i]
                genres += g.name
                if (i != movie.genres.size - 1) genres += ","
            }
        }

        return genres
    }

    class MovieHolder(val movieCardBinding: MovieCardBinding) :
        RecyclerView.ViewHolder(movieCardBinding.root)
}