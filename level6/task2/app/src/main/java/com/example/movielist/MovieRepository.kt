package com.example.movielist

import com.example.movielist.api.MovieApi
import com.example.movielist.api.MovieApiService

class MovieRepository {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getMostPopularMovies(year: String) = movieApi.getMostPopularMovies(year)
}