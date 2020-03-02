package com.portofolio.mymovieapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.portofolio.mymovieapp.Data.Room.Movie
import com.portofolio.mymovieapp.R
import okhttp3.HttpUrl

/**
 * Created by paul on 2/17/2020 at 8:43 AM.
 */
class TrendingMoviesPagedAdapter(val context: Context): PagedListAdapter<Movie, TrendingMoviesPagedAdapter.ViewHolder>(
    object: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.trending_movies_single_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        val imageUrl = HttpUrl.get("https://image.tmdb.org/t/p/w500${movie!!.posterPath}")
        holder.posterImage.load(imageUrl)
        holder.titleTv.text = movie.title
        holder.movieDescription.text = movie.overview

        holder.onItemClickedListener(movie)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val posterImage: ImageView = itemView.findViewById(R.id.trending_movie_poster)
        val titleTv: TextView = itemView.findViewById(R.id.trending_movie_title)
        val movieDescription: TextView = itemView.findViewById(R.id.trending_movie_description)


        init {


            itemView.setOnClickListener {


            }
        }

        fun onItemClickedListener(movie: Movie) = View.OnClickListener{
            val bundle = Bundle()
            bundle.putParcelable("movie", movie)
            itemView.findNavController().navigate(R.id.action_trendingMoviesFragment_to_movieDetailFragment, bundle)
        }
    }
}