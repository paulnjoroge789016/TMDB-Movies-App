package com.portofolio.mymovieapp.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by paul on 1/27/2020 at 8:52 PM.
 */
@Parcelize
data class MovieTrailer(val id: String, var key: String)
    :Parcelable

@Parcelize
data class MovieTrailers(val results: ArrayList<MovieTrailer>)
    :Parcelable