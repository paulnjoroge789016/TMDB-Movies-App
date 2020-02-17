package com.portofolio.moviesapp.Repository


import com.portofolio.moviesapp.Models.Cast
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.Retrofit.RetrofitInterface
import com.portofolio.mymovieapp.Data.Room.CastDao
import com.portofolio.mymovieapp.Data.Room.MovieDao
import com.portofolio.mymovieapp.Data.Room.MovieTrailerDao
import com.portofolio.mymovieapp.mappers.EntityMappers
import com.portofolio.mymovieapp.models.MovieTrailer

/**
 * Created by paul on 12/26/2019 at 2:37 PM.
 */
class MovieRepository(private val api: RetrofitInterface,private val  castDao: CastDao,
                      private val movieTrailerDao: MovieTrailerDao,
                      private val movieDao: MovieDao): BaseRepository(){

    suspend fun getPopularMovies(page: Int): ArrayList<Movie> {
        val movieResponse = safeApiCall(
            call = { api.getUpcoming(page).await() },
            error = "Failed to get Movies"
        )

        val moviesList = arrayListOf<com.portofolio.mymovieapp.Data.Room.Movie>()
        movieResponse!!.results.forEach {
            moviesList.add(EntityMappers.toDb(it))
        }

        val finalMovieList = moviesList.toList()
        movieDao.insertAllMovies(finalMovieList)

        return movieResponse!!.results
    }

    suspend fun getCast(id: Int): ArrayList<Cast>{

        val castResponse = safeApiCall(
            call = {api.getCast(id).await()},
            error = "Failed to get cast"
        )

        val castsList = arrayListOf<com.portofolio.mymovieapp.Data.Room.Cast>()
        castResponse!!.cast.forEach {
            castsList.add(EntityMappers.fromCastEntityToDb(it))
        }

        castDao.insertAllCasts(castsList.toList())
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

        val trailers = arrayListOf<com.portofolio.mymovieapp.Data.Room.MovieTrailer>()
        movieTrailers!!.results.forEach {
            trailers.add(EntityMappers.fromTrailerEntityToDb(it))
        }

        movieTrailerDao.insertAllTrailers(trailers.toList())

        return movieTrailers!!.results
    }
}