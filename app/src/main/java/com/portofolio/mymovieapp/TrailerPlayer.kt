package com.portofolio.mymovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeBaseActivity

import com.google.android.youtube.player.YouTubePlayerView
import com.portofolio.mymovieapp.Models.MovieTrailer
import com.portofolio.mymovieapp.Utils.YoutubeVideoListener

class TrailerPlayer :Fragment() {


    private val YOUTUBE_API = "AIzaSyDLgtrUMgki6w2m6BT57s0CzApi_yxSmC4"
    lateinit var movieTrailers: ArrayList<MovieTrailer>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trailer_player, container, false)

        movieTrailers = arguments?.getParcelableArrayList<MovieTrailer>("movieTrailers")!!

        val trailerVideoView = view.findViewById<YouTubePlayerView>(R.id.trailer_video_view)
        trailerVideoView.initialize(YOUTUBE_API, YoutubeVideoListener(movieTrailers[0]))
        return view
    }





}
