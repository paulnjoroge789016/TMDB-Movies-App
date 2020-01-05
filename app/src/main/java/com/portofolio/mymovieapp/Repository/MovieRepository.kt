package com.portofolio.moviesapp.Repository


import androidx.lifecycle.MutableLiveData
import com.portofolio.moviesapp.Models.Cast
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.Retrofit.RetrofitInterface
import com.portofolio.moviesapp.ViewModels.TmdbViewModel

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
}