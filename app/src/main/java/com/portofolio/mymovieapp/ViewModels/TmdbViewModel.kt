package com.portofolio.moviesapp.ViewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.portofolio.moviesapp.Models.Cast
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.Repository.MovieRepository
import com.portofolio.moviesapp.Services.MoviesApiService
import kotlinx.coroutines.*

import kotlin.coroutines.CoroutineContext

/**
 * Created by paul on 12/26/2019 at 10:14 PM.
 */
 class TmdbViewModel: ViewModel() {


        private var parentJob: Job = Job()

    val coroutineContext: CoroutineContext
      get() = parentJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository = MovieRepository(MoviesApiService.moviesApi)

    val view = MutableLiveData<View>()
    val popularMoviesLiveData = MutableLiveData<ArrayList<Movie>>()

    val castListLiveData = MutableLiveData<ArrayList<Cast>>()
   fun fetchMovies(page: Int){
       scope.launch {
               popularMoviesLiveData.postValue(repository.getPopularMovies(page))
       }
   }

    fun getCast(id: Int){
        scope.launch {
            castListLiveData.postValue(repository.getCast(id))
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}