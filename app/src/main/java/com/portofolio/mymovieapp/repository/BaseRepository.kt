package com.portofolio.moviesapp.Repository

import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * Created by paul on 12/26/2019 at 2:10 PM.
 */
open class BaseRepository {
    suspend fun <T: Any> safeApiCall(call: suspend()-> Response<T>, error: String) :T?{
        val result = moviesApiOutput(call, error)
        var output: T? = null
        when(result){
            is Result.Success ->
                output = result.data
            is Result.Error -> {
                Log.e("Error", result.exception.toString())
            }
        }
        return output
    }

    private suspend fun <T: Any> moviesApiOutput(call: suspend() -> Response<T>, error : String) : Result<T>{
        val response = call.invoke()
        return if(response.isSuccessful)
            Result.Success(response.body()!!)
        else
            Result.Error(IOException("Oops .. something went wrong due to $error"))
    }
}