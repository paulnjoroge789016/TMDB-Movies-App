package com.portofolio.mymovieapp.Data.Room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

/**
 * Created by paul on 2/15/2020 at 1:30 PM.
 */

@Dao
interface MovieTrailerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrailer(movieTrailer: MovieTrailer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTrailers(movieTrailers: List<MovieTrailer>)

    @Delete
    suspend fun deleteTrailer(vararg movieTrailer: MovieTrailer)

    @Query("DELETE from trailers_tb")
    suspend fun deleteAllTrailers()

    @Query("SELECT * from trailers_tb")
    fun getAllTrailers(): DataSource.Factory<Int, MovieTrailer>

    @Query("SELECT * from trailers_tb WHERE id = :id")
    fun getTrailerById(id: Int): LiveData<MovieTrailer>
}