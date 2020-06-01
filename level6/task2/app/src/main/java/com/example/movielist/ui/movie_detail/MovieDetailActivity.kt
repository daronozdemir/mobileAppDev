package com.example.movielist.ui.movie_detail

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initViews()
    }

    private fun initViews() {
        val movie = intent.getParcelableExtra<Movie>(MOVIE_DETAIL)

        if(movie != null){
            Glide.with(this).load(movie.getBackdropUrl()).into(ivBackdrop)
            Glide.with(this).load(movie.getPosterUrl()).into(ivPoster)
            tvTitle.text = movie.title
            tvRelease.text = getString(R.string.release, movie.releaseDate)
            tvRating.text = movie.rating.toString()
            tvOverview.text = movie.overview
        } else finish()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val MOVIE_DETAIL = "MOVIE_DETAIL"
    }
}
