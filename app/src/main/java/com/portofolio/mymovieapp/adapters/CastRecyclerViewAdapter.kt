package com.portofolio.moviesapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.portofolio.moviesapp.Models.Cast
import com.portofolio.mymovieapp.R
import okhttp3.HttpUrl

/**
 * Created by paul on 12/29/2019 at 5:28 PM.
 */
class CastRecyclerViewAdapter()  : RecyclerView.Adapter<CastRecyclerViewAdapter.ViewHolder>() {

    var castList: List<Cast>? = ArrayList()
    lateinit var context: Context
    lateinit var inflater: LayoutInflater

    constructor(movieList: List<Cast>, context: Context) : this() {
        this.castList = movieList
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.cast_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return castList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast = castList!![position]
        holder.castName.text = cast.name
        val backdropURL = HttpUrl.get("https://image.tmdb.org/t/p/w500${cast.profile_path}")
        holder.castImageView.load(backdropURL)


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var castImageView: ImageView
        lateinit var castName: TextView
        var pos: Int = 0
        init {
            castImageView = itemView.findViewById(R.id.cast_image)
            castName = itemView.findViewById(R.id.cast_name)
            itemView.setOnClickListener {
            }
        }
    }
}