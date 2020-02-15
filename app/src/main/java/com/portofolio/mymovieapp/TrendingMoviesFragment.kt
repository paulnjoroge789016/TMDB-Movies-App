package com.portofolio.mymovieapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portofolio.moviesapp.ViewModels.TmdbViewModel
import com.portofolio.mymovieapp.adapters.TrendingMoviesRecyclerViewAdapter

/**
 * A simple [Fragment] subclass.
 */
class TrendingMoviesFragment : Fragment() {

    val tmdbViewModel by lazy {
        ViewModelProviders.of(this)[TmdbViewModel::class.java]
    }

    lateinit var trendingMoviesAdapter: TrendingMoviesRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_trending_movies, container, false)

        tmdbViewModel.getTrendingMovies(1)

        val trendingMoviesRecyclerView = view.findViewById<RecyclerView>(R.id.trending_movies_rv)
        val lm = GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
        trendingMoviesRecyclerView.layoutManager = lm


        tmdbViewModel.trendingMoviesArrayList.observe(this, Observer {
            trendingMoviesAdapter = TrendingMoviesRecyclerViewAdapter(it, view.context)
            trendingMoviesRecyclerView.adapter = trendingMoviesAdapter
        })


        return view
    }


}
