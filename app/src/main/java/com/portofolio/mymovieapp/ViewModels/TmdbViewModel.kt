package com.portofolio.moviesapp.ViewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.portofolio.moviesapp.Models.Cast
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.Repository.MovieRepository
import com.portofolio.moviesapp.Services.MoviesApiService
import com.portofolio.mymovieapp.Models.MovieTrailer
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
    val trendingMoviesArrayList =  MutableLiveData<ArrayList<Movie>>()
    val movieTrailers =  MutableLiveData<ArrayList<MovieTrailer>>()

   fun fetchMovies(page: Int){
       scope.launch(Dispatchers.IO) {
                    popularMoviesLiveData.postValue(repository.getPopularMovies(page))
       }
   }

    fun getCast(id: Int){
        scope.launch {
            castListLiveData.postValue(repository.getCast(id))
        }
    }

    fun getTrendingMovies(page: Int) = scope.launch{
        trendingMoviesArrayList.postValue(repository.getTrendingMovies(page))
    }

    fun getMovieTrailers(id: Int) = scope.launch {
//        val movieTrailerss = repository.getMovieTrailes(id)
//        movieTrailerss.forEach{
//            val url = "https://www.youtube.com/watch?v=${it.key}"
//            it.key = url
//        }
        movieTrailers.postValue(repository.getMovieTrailes(id))
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}