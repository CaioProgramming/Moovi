package com.ilustris.moovi.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
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
        val movieCardBinding: MovieCardBinding = (holder as MovieHolder).movieCardBinding
        val movie = movies?.get(position)

        movie?.let {
            movieCardBinding.movie = movie
            //loadPoster(movie.poster_path,movieCardBinding.moviePicture)
            val inAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
            movieCardBinding.movieCard.startAnimation(inAnimation)
            Thread(Runnable {
                getMovieGenres(it, movieCardBinding)
            }).start()
        }


    }

    fun updateMovieInfo(movie: Movie, genres: String, movieCardBinding: MovieCardBinding) {
        movieCardBinding.movie = movie
        movieCardBinding.movieDetails.text = "${Utils.convertDate(movie.release_date)} $genres"
        loadPoster(NetworkUtils.postersPath + movie.poster_path, movieCardBinding.moviePicture)
    }


    private fun loadPoster(url: String, imageView: ImageView) {
        Glide.with(context).load(url).error(R.drawable.ic_clapperboard).into(imageView)
    }

    private fun getMovieGenres(movie: Movie, movieCardBinding: MovieCardBinding) {
        MoviePresenter(object : MvpContract.ViewImpl {
            override fun viewBinding(): ViewBinding {
                return movieCardBinding
            }

            override fun showLoad(load: Boolean) {
                Log.i("MoviesAdapter", "loading? $load")
            }

            override fun showMovieData(movie: Movie) {
                movie.genres.let {
                    var genres = ""
                    for (i in it.indices) {
                        val g = movie.genres[i]
                        genres += g.name
                        if (i < movie.genres.size - 1) genres += ", "
                    }
                    updateMovieInfo(movie, genres, movieCardBinding)
                }
            }

            override fun showSimilarMovies(movies: List<Movie>) {}
        }).findMovie(movie.id, false)

    }

    class MovieHolder(val movieCardBinding: MovieCardBinding) :
        RecyclerView.ViewHolder(movieCardBinding.root)
}