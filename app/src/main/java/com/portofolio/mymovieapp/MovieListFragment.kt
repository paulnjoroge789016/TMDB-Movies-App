package com.portofolio.mymovieapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portofolio.moviesapp.Adapters.PopularMoviesRvAdapter
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.ViewModels.TmdbViewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    var myView: View?  = null
    var moviesArrayList = ArrayList<Movie>()
    var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (myView == null) {
            myView = inflater.inflate(R.layout.fragment_movie_list2, container, false)
        }
        else{
            return myView
        }


        val myMoviesRecyclerView = myView!!.findViewById<RecyclerView>(R.id.popularMoviesRecyclerView)
        val lm = LinearLayoutManager(myView!!.context, LinearLayoutManager.VERTICAL, false)
        val tmdbViewModel = ViewModelProviders.of(this)[TmdbViewModel::class.java]

        if(moviesArrayList.isEmpty())
            tmdbViewModel.fetchMovies(page)

        tmdbViewModel.popularMoviesLiveData.observe(this, Observer {
            moviesArrayList = it
            val myRecyclerViewAdapter = PopularMoviesRvAdapter(moviesArrayList, myView!!.context)
            myMoviesRecyclerView.adapter = myRecyclerViewAdapter
            myMoviesRecyclerView.layoutManager = lm
        })

//        myMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val visibleItemCount = lm.childCount;
//                val totalItemCount = lm.itemCount;
//                val firstVisibleItemPosition = lm.findFirstVisibleItemPosition();
//                if (!isLoading() && !isLastPage()) {
//                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
//                        && firstVisibleItemPosition >= 0) {
//                        Log.i(TAG, "Loading more items")
//                        loadMoreItems();
//                    }
//                }
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//        })
        return myView
    }


}
