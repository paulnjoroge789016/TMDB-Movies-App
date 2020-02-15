package com.portofolio.mymovieapp.mappers

import com.portofolio.moviesapp.Models.Cast
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.mymovieapp.models.MovieTrailer

/**
 * Created by paul on 2/15/2020 at 3:56 PM.
 */
object EntityMappers {
    fun toDb(from: Movie) = com.portofolio.mymovieapp.Data.Room.Movie(from.id, from.poster_path,
        from.overview, from.release_date, from.original_title, from.original_language, from.title,
        from.backdrop_path,from.popularity, from.vote_average)

    fun fromDb(from: com.portofolio.mymovieapp.Data.Room.Movie) = Movie(from.id, from.posterPath,
        from.overview, from.releaseDate, from.originalTitle, from.originalLanguage, from.title,
        from.backdropPath, from.popularity, from.voteAverage)

    fun fromCastEntityToDb(from: Cast) = com.portofolio.mymovieapp.Data.Room.Cast(0, from.character, from.name, from.profile_path)

    fun toCastEntityFromDb(from: com.portofolio.mymovieapp.Data.Room.Cast) = Cast(from.character,
        from.name, from.profilePath)


    fun fromTrailerEntityToDb(from: MovieTrailer) = com.portofolio.mymovieapp.Data.Room.MovieTrailer(from.id.toInt(), from.key)

    fun fromTrailerEntityFromDb(from: com.portofolio.mymovieapp.Data.Room.MovieTrailer)= MovieTrailer(
        from.id.toString(),
        from.key)
}