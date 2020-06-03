package com.ilustris.moovi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.ilustris.moovi.databinding.ActivityMainBinding
import com.ilustris.moovify.model.Movie
import com.ilustris.moovify.utils.MovieService
import com.ilustris.moovify.utils.NetworkUtils
import com.ilustris.moovify.utils.NetworkUtils.postersPath
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actbind: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(actbind.root)

        val movieID = 299534
        val call = MovieService().getMovie().movie(movieID.toString() + NetworkUtils.defaultconfig)
        Log.d(localClassName, "Making call...")
        call.enqueue(object : Callback<Movie?> {
            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                Log.e("Callback fail", t.message!!)
            }

            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                Log.i("Callback", "response ${response.message()}")
                Log.d("Callback", "called ${response.raw().request().url()}")
                Toast.makeText(this@MainActivity, "response ${response.body()}", Toast.LENGTH_LONG)
                    .show()
                response.body()?.let {
                    loadMovieInfo(it)
                }
            }

        })
    }


    fun loadMovieInfo(movie: Movie) {
        Glide.with(this).load(postersPath + movie.poster_path).into(moviePicture)
        movieName.text = movie.title
        moviePopularity.text = "${movie.popularity} views"
        movieLikes.text = "${movie.vote_average} likes"

    }
}
