package com.example.movielist.model

import android.content.res.Resources
import android.os.Parcelable
import com.example.movielist.R
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("title") var title: String? = null,
    @SerializedName("poster_path") var posterImage: String? = null,
    @SerializedName("backdrop_path") var backdropImage: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("vote_average") var rating: Float? = null,
    @SerializedName("overview") var overview: String? = null
) : Parcelable {
    fun getPosterUrl() : String {
        return "https://image.tmdb.org/t/p/w500/${this.posterImage}"
    }

    fun getBackdropUrl(): String {
        return "https://image.tmdb.org/t/p/original/${this.backdropImage}"
    }
}
