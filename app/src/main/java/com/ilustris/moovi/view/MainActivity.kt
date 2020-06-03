package com.ilustris.moovi.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.ilustris.moovi.R
import com.ilustris.moovi.adapters.SimilarMoviesAdapter
import com.ilustris.moovi.databinding.ActivityMainBinding
import com.ilustris.moovi.model.contract.MvpContract
import com.ilustris.moovi.presenter.MoviePresenter
import com.ilustris.moovify.model.Movie
import com.ilustris.moovify.utils.NetworkUtils.postersPath
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MvpContract.ViewImpl {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding().root)
        val moviePresenter = MoviePresenter(this)
        showLoad(true)
        moviePresenter.findMovie(299534, true)

    }

    override fun viewBinding(): ViewBinding {
        val actbind =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        return actbind
    }


    override fun showLoad(load: Boolean) {
        val handler = Handler()
        handler.postDelayed({
            if (load) topshimmer.startShimmer() else topshimmer.hideShimmer()
        }, 3000)
    }

    override fun showMovieData(movie: Movie) {
        Glide.with(this).load(postersPath + movie.poster_path).error(R.drawable.ic_clapperboard)
            .into(moviePicture)
        movieName.text = movie.title
        moviePopularity.text = "${movie.popularity} views"
        movieLikes.text = "${movie.vote_average} likes"
    }


    override fun showSimilarMovies(movies: List<Movie>) {
        val moviesAdapter = SimilarMoviesAdapter(this, movies)
        suggestedMovies.adapter = moviesAdapter
        suggestedMovies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}
