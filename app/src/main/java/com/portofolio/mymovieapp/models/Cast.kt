package com.portofolio.moviesapp.Models

/**
 * Created by paul on 12/29/2019 at 5:39 PM.
 */
data class Cast(val character: String, val name: String,val profile_path: String)

data class credits(val cast: ArrayList<Cast>)
