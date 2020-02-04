package com.portofolio.mymovieapp

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerView
import com.portofolio.mymovieapp.Models.MovieTrailer
import com.portofolio.mymovieapp.Utils.YoutubeVideoListener

class YoutubeVideoPlayer : YouTubeBaseActivity() {


    private val YOUTUBE_API = "AIzaSyDLgtrUMgki6w2m6BT57s0CzApi_yxSmC4"
    lateinit var movieTrailers: ArrayList<MovieTrailer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_video_player)

        movieTrailers = intent?.getParcelableArrayListExtra<MovieTrailer>("movieTrailers")!!

        val trailerVideoView = findViewById<YouTubePlayerView>(R.id.trailer_video_view)

        trailerVideoView.initialize(YOUTUBE_API, YoutubeVideoListener(movieTrailers[0]))

    }
}