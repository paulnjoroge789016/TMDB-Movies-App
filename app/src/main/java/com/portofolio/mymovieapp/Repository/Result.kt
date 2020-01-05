package com.portofolio.moviesapp.Repository

import java.lang.Exception

/**
 * Created by paul on 12/26/2019 at 1:26 PM.
 */
 sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}

