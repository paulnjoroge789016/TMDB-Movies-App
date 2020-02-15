package com.portofolio.mymovieapp.Utils

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.portofolio.mymovieapp.models.MovieTrailer

/**
 * Created by paul on 1/28/2020 at 10:10 AM.
 */
class YoutubeVideoListener(val movieTrailer: MovieTrailer) : YouTubePlayer.OnInitializedListener  {
    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {

        if (!p2) {
            p1!!.cueVideo(movieTrailer.key)
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {

    }
}