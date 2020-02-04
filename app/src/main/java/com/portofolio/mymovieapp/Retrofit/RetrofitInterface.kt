package com.portofolio.moviesapp.Retrofit

import com.portofolio.moviesapp.Models.MoviesList
import com.portofolio.moviesapp.Models.credits

import com.portofolio.mymovieapp.Models.MovieTrailers
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by paul on 12/26/2019 at 12:23 PM.
 */
interface RetrofitInterface {


    @GET("movie/upcoming?api_key=2e417461a5c52546847623467482f881&language=en-US")
    fun getUpcoming(@Query("page")  page: Int) : Deferred<Response<MoviesList>>

    @GET("movie/{movie_id}/credits?api_key=2e417461a5c52546847623467482f881")
    fun getCast(@Path("movie_id") id: Int): Deferred<Response<credits>>

    @GET("movie/popular?api_key=2e417461a5c52546847623467482f881&language=en-US")
    fun getTrending(@Query("page")  page: Int) : Deferred<Response<MoviesList>>

    @GET("movie/{movie_id}/videos?api_key=2e417461a5c52546847623467482f881&language=en-US")
    fun getVideos(@Path("movie_id") id: Int): Deferred<Response<MovieTrailers>>
}