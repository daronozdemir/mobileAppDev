package com.example.movielist.api

import com.example.movielist.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("discover/movie?api_key=ea7a8dffa1f0288a5795337a89c8a52d&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMostPopularMovies(@Query("primary_release_year") year: String): Call<MovieResponse>
}