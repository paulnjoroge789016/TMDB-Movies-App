package com.portofolio.mymovieapp.Data.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by paul on 2/15/2020 at 12:11 PM.
 */
@Entity(tableName = "casts_tb")
 data class Cast (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "character") val character: String,
    @ColumnInfo(name = "character_name") val name: String,
    @ColumnInfo(name = "profile_path") val profilePath: String)
