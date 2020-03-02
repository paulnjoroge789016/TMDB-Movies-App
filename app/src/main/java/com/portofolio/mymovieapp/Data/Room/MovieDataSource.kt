package com.portofolio.mymovieapp.Data.Room

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.portofolio.moviesapp.Retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope

/**
 * Created by paul on 2/17/2020 at 6:46 PM.
 */
class MovieDataSource(
    private val moviesApiService: RetrofitInterface,
     private val moviesAppDatabase: MoviesAppDatabase
): PagedList.BoundaryCallback<Movie>(){
    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)
        moviesApiService.getTrending(2)
    }
}