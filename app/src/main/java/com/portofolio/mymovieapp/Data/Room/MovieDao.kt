package com.portofolio.mymovieapp.Data.Room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

/**
 * Created by paul on 2/15/2020 at 12:34 PM.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Movie>)

    @Query("SELECT * from movies_table")
   fun getAllMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * from movies_table WHERE id = :id ")
    fun getMovieById(id: Int): LiveData<Movie>

    @Delete()
    suspend fun deleteMovie(vararg movie: Movie)

    @Query("Delete from movies_table")
    suspend fun deleteAllMovies()
}