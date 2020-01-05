package com.portofolio.moviesapp.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.mymovieapp.R
import okhttp3.HttpUrl

/**
 * Created by paul on 12/29/2019 at 10:43 AM.
 */
class PopularMoviesRvAdapter() : RecyclerView.Adapter<PopularMoviesRvAdapter.ViewHolder>() {


    var movieList: ArrayList<Movie>? = ArrayList()
    lateinit var context: Context
    lateinit var inflater: LayoutInflater

    constructor(movieList: ArrayList<Movie>?, context: Context) : this() {
        this.movieList = movieList
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.home_list_single_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return movieList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList!![position]
        holder.movieTitle.text = movie.original_title
        holder.movieLanguage.text = movie.original_language
        holder.movieReleaseDate.text = movie.release_date
        holder.movieRating.text = movie.vote_average.toString()

        val rating = (movie.vote_average/10) * 5
        holder.movieRatingBar.apply {
            numStars = 5
            setRating(rating.toFloat())
        }

        val imageUrl = HttpUrl.get("https://image.tmdb.org/t/p/w500${movie.poster_path}")
        holder.movieImageView.load(imageUrl)
    }

    

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var movieImageView: ImageView
         var movieTitle: TextView
         var movieRatingBar: RatingBar
         var movieReleaseDate: TextView
         var movieLanguage: TextView
         var movieRating: TextView
        var pos: Int = 0
        init {
            movieImageView = itemView.findViewById(R.id.moviePosterImageView)
            movieTitle = itemView.findViewById(R.id.movieTitleTv)
            movieRatingBar = itemView.findViewById(R.id.movieRatingBar)
            movieReleaseDate = itemView.findViewById(R.id.movieReleaseDateTv)
            movieLanguage = itemView.findViewById(R.id.movieLanguageTv)
            movieRating = itemView.findViewById(R.id.movieRatingTv)

            itemView.setOnClickListener {
                
                pos  = layoutPosition
                val bundle: Bundle = Bundle()
                val clickedMovie = movieList!![pos]
                bundle.putParcelable("movie", clickedMovie)
                itemView.findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)
            }
        }



    }
}