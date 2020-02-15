package com.portofolio.mymovieapp.Data.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by paul on 2/15/2020 at 1:44 PM.
 */
@Database(entities = [Cast::class, Movie::class, MovieTrailer::class], version = 1, exportSchema = false)
abstract class MoviesAppDatabase: RoomDatabase() {
    abstract fun castDao(): CastDao
    abstract fun movieDao(): MovieDao
    abstract fun movieTrailerDao(): MovieTrailerDao


    companion object {
        @Volatile   private var INSTANCE: MoviesAppDatabase? = null

        fun getDatabase(context: Context): MoviesAppDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    MoviesAppDatabase::class.java,
                    "MoviesAppDatabase").build()


                INSTANCE = instance
                instance
            }
        }
    }
}