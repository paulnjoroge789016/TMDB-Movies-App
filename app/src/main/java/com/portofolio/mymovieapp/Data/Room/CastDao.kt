package com.portofolio.mymovieapp.Data.Room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

/**
 * Created by paul on 2/15/2020 at 1:21 PM.
 */
@Dao
interface CastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(cast: Cast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCasts(casts: List<Cast>)

    @Query("SELECT * from casts_tb")
     fun getAll(): DataSource.Factory<Int, Cast>

    @Query("SELECT * from casts_tb WHERE id = :id")
     fun getCast(id: Int): LiveData<Cast>

    @Delete
    fun deleteCast(vararg cast: Cast)

    @Query("DELETE from casts_tb")
    fun deleteAllCasts()

}