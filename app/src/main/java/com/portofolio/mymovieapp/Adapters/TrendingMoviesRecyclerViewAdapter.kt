package com.portofolio.mymovieapp.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.mymovieapp.R
import okhttp3.HttpUrl

/**
 * Created by paul on 1/24/2020 at 10:37 AM.
 */
class TrendingMoviesRecyclerViewAdapter(var trendingMoviesArrayList: ArrayList<Movie>, var context: Context): RecyclerView.Adapter<TrendingMoviesRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.trending_movies_single_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return trendingMoviesArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = trendingMoviesArrayList[position]

        val imageUrl = HttpUrl.get("https://image.tmdb.org/t/p/w500${movie.poster_path}")
        holder.posterImage.load(imageUrl)
        holder.titleTv.text = movie.title
        holder.movieDescription.text = movie.overview
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImage: ImageView = itemView.findViewById(R.id.trending_movie_poster)
        val titleTv: TextView = itemView.findViewById(R.id.trending_movie_title)
        val movieDescription: TextView = itemView.findViewById(R.id.trending_movie_description)


        init {


            itemView.setOnClickListener {

                val pos  = layoutPosition
                val bundle = Bundle()
                val clickedMovie = trendingMoviesArrayList[pos]
                bundle.putParcelable("movie", clickedMovie)
                itemView.findNavController().navigate(R.id.action_trendingMoviesFragment_to_movieDetailFragment, bundle)
            }
        }
    }
}