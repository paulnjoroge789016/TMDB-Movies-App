package com.portofolio.moviesapp.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by paul on 12/26/2019 at 12:06 PM.
 */
@Parcelize
data class Movie(val id: Int, val poster_path: String, val overview: String, val release_date: String,
                 val original_title: String, val original_language: String,
                 val title: String, val backdrop_path: String?, val popularity: Double,
                 val vote_average: Double) : Parcelable

data class MoviesList(val total_page: Int, val results: ArrayList<Movie>)