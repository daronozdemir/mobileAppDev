package com.example.movielist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movielist.MovieRepository
import com.example.movielist.model.Movie
import com.example.movielist.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()
    val movies = MutableLiveData<List<Movie>>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>(false)

    /**
     * Gets the top movies from the repository using Retrofit.
     * onResponse if the response is successful populate the [movies] object.
     * If the call encountered an error then populate the [error] object.
     */
    fun getMostPopularMovies(year: String) {
        loading.apply { value = true }
        movieRepository.getMostPopularMovies(year).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) movies.value = response.body()?.results
                else error.value = "An error occurred while fetching the movies: ${response.errorBody().toString()}"
                loading.apply { value = false }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                error.value = t.message
                loading.apply { value = false }
            }
        })
    }
}