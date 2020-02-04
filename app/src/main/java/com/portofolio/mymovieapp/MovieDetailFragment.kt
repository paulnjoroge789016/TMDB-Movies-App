package com.portofolio.mymovieapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.portofolio.moviesapp.Adapters.CastRecyclerViewAdapter
import com.portofolio.moviesapp.Models.Cast
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.ViewModels.TmdbViewModel
import com.portofolio.mymovieapp.Models.MovieTrailer
import com.portofolio.mymovieapp.Utils.YoutubeVideoListener
import kotlinx.android.synthetic.main.fragment_movie_detail2.*
import okhttp3.HttpUrl
import java.util.ArrayList


class MovieDetailFragment  : Fragment(){


    lateinit var tmdbViewModel: TmdbViewModel
    lateinit var movieTrailers: ArrayList<MovieTrailer>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_detail2, container, false)
        val movie = arguments?.getParcelable<Movie>("movie")

        val backdropURL = HttpUrl.get("https://image.tmdb.org/t/p/w500${movie!!.backdrop_path}")


        val image  = view.findViewById<ImageView>(R.id.movieDetailImageView)
        image.load(backdropURL)
        val movieDetailReleaseDate = view.findViewById<TextView>(R.id.movieDetailReleaseDate)
        movieDetailReleaseDate.text = "release date     |   ${movie.release_date}"
        val movieDetailVotes = view.findViewById<TextView>(R.id.movieDetailVotes)
        movieDetailVotes.text = movie.vote_average.toString()
        val movieDetailDescription = view.findViewById<TextView>(R.id.movieDetailDescription)
        movieDetailDescription.text = movie.overview

        val movieDetailTitle = view.findViewById<TextView>(R.id.movie_detail_title)
        movieDetailTitle.text = movie.title


        var casts: ArrayList<Cast>
        tmdbViewModel = ViewModelProviders.of(this)[TmdbViewModel::class.java]
        tmdbViewModel.getCast(movie.id)
        tmdbViewModel.castListLiveData.observe(this, Observer {
            casts = it
            val trimmedCast: List<Cast> = if(casts.size > 10) {
                casts.subList(0, 10).toList()
            } else{
                casts
            }
            val lm = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            castRecyclerView.layoutManager = lm
            val castAdapter = CastRecyclerViewAdapter(trimmedCast, view.context)
            castRecyclerView.adapter =castAdapter
        })



        tmdbViewModel.getMovieTrailers(movie.id)

        tmdbViewModel.movieTrailers.observe(this, Observer {
            movieTrailers = it
        })


        view.findViewById<ImageView>(R.id.play_trailer_btn).setOnClickListener {

            val intent = Intent(view.context, YoutubeVideoPlayer::class.java)
            intent.putParcelableArrayListExtra("movieTrailers", movieTrailers)
            startActivity(intent)
//            if(movieTrailers.size < 0) {
//                Toast.makeText(view.context, "there are no trailers for this movie", Toast.LENGTH_LONG).show()
//
//            }
//            else{
//                createTrailersDialog()
//            }
        }

        return view
    }


    private fun createTrailersDialog(){

        val trailerView = LayoutInflater.from(view!!.context).inflate(R.layout.trailer_layout_view, null)
        val builder = AlertDialog.Builder(view!!.context)
        builder.setView(trailerView)
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
        val trailerVideoView = alertDialog.findViewById<YouTubePlayerView>(R.id.trailer_video_view)

    }
}
