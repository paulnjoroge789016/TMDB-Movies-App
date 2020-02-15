package com.portofolio.mymovieapp.Data.Room

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by paul on 2/15/2020 at 12:03 PM.
 */
@Entity(tableName = "movies_table")
data class Movie(@PrimaryKey(autoGenerate = false) val id: Int = 0,
                 @ColumnInfo(name = "poster_path" ) val posterPath: String,
                 @ColumnInfo(name = "overview" ) val overview: String,
                 @ColumnInfo(name = "release_date" ) val releaseDate: String,
                 @ColumnInfo(name = "original_title" ) val originalTitle: String,
                 @ColumnInfo(name = "original_language" ) val originalLanguage: String,
                 @ColumnInfo(name = "title" ) val title: String,

                 @Nullable
                 @ColumnInfo(name = "backdrop_path" ) val backdropPath: String?,
                 @ColumnInfo(name = "popularity" ) val popularity: Double,
                 @ColumnInfo(name = "vote_average" ) val voteAverage: Double
                 )