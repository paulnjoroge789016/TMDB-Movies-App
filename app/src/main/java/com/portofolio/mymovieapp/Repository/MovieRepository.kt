package com.portofolio.moviesapp.Repository


import com.portofolio.moviesapp.Models.Cast
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.Retrofit.RetrofitInterface
import com.portofolio.mymovieapp.Models.MovieTrailer

/**
 * Created by paul on 12/26/2019 at 2:37 PM.
 */
class MovieRepository(private val api: RetrofitInterface): BaseRepository(){

    suspend fun getPopularMovies(page: Int): ArrayList<Movie> {
        val movieResponse = safeApiCall(
            call = { api.getUpcoming(page).await() },
            error = "Failed to get Movies"
        )

        return movieResponse!!.results
    }

    suspend fun getCast(id: Int): ArrayList<Cast>{
        val castResponse = safeApiCall(
            call = {api.getCast(id).await()},
            error = "Failed to get cast"
        )
        return castResponse!!.cast
    }

    suspend fun getTrendingMovies(page :Int): ArrayList<Movie>{
        val trendingMoviesList = safeApiCall(
            call = {api.getTrending(page).await()},
            error = "failed to get trending movies"
        )

        return trendingMoviesList!!.results
    }

    suspend fun getMovieTrailes(id :Int): ArrayList<MovieTrailer>{
        val movieTrailers = safeApiCall(
            call = {api.getVideos(id).await()},
            error = "failed to get trending movies"
        )



        return movieTrailers!!.results
    }
}