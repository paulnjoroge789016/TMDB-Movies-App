package com.portofolio.mymovieapp.Data.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by paul on 2/15/2020 at 12:33 PM.
 */
@Entity(tableName = "trailers_tb")
data class MovieTrailer(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "key") val key: String
)