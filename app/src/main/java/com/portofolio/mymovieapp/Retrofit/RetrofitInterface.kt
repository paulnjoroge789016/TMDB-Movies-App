package com.portofolio.moviesapp.Retrofit

import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.Models.MoviesList
import com.portofolio.moviesapp.Models.credits
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by paul on 12/26/2019 at 12:23 PM.
 */
interface RetrofitInterface {


    @GET("movie/upcoming?api_key=2e417461a5c52546847623467482f881&language=en-US&page=1")
    fun getUpcoming(@Query("page")  page: Int) : Deferred<Response<MoviesList>>

    @GET("movie/{id}?api_key=2e417461a5c52546847623467482f881&append_to_response=credits")
    fun getCast(@Path("id") id: Int): Deferred<Response<credits>>
}