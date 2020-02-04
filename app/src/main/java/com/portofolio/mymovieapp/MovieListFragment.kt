package com.portofolio.mymovieapp


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portofolio.moviesapp.Adapters.PopularMoviesRvAdapter
import com.portofolio.moviesapp.Models.Movie
import com.portofolio.moviesapp.ViewModels.TmdbViewModel
import kotlinx.android.synthetic.main.fragment_movie_list2.*
import kotlinx.coroutines.delay

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    var myView: View?  = null
    var moviesArrayList = ArrayList<Movie>()
    var page = 1
    var isLoading = false
    val TAG = "DEBUG MODE"
    var myRecyclerViewAdapter: PopularMoviesRvAdapter? = null
    var previuosTotal = 0
    var visibleThreshold = 5
    var test = 0
    lateinit var tmdbViewModel: TmdbViewModel
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
        tmdbViewModel = ViewModelProviders.of(this)[TmdbViewModel::class.java]

        if(moviesArrayList.isEmpty())
            tmdbViewModel.fetchMovies(page)



        myMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled( recyclerView: RecyclerView, dx: Int, dy: Int
                ) {
                    super.onScrolled(recyclerView, dx, dy)

                        val visibleItemCount = lm.childCount
                        val totalItemCount = lm.itemCount
                        val firstVisible = lm.findFirstVisibleItemPosition()

                    if(dy > 0) {
                        if (isLoading) {
                            if (totalItemCount > previuosTotal) {
                                isLoading = false
                                previuosTotal = totalItemCount
                            }
                        }

                        if (!isLoading && (totalItemCount - visibleItemCount) <=
                            (firstVisible + visibleThreshold)
                        ) {
                            test += 1
                            fetchMoreData()
                            isLoading = true
                        }
                    }
                }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }
        })
        tmdbViewModel.popularMoviesLiveData.observe(this, Observer {
            if(page == 1) {
                moviesArrayList = it
                myRecyclerViewAdapter = PopularMoviesRvAdapter(moviesArrayList, myView!!.context)
                myMoviesRecyclerView.adapter = myRecyclerViewAdapter
                    myMoviesRecyclerView.layoutManager = lm
            }
            else{
                val startPosition = moviesArrayList.size - 1
                moviesArrayList.addAll(it)
                myMoviesRecyclerView.adapter!!.notifyItemRangeInserted(startPosition, 20)
            }
        })
        return myView
    }


    fun fetchMoreData(){

        if (test == 2) {
            test = 1

            val previousPage = page
            val newPage = previousPage + 1
            page = newPage
            Toast.makeText(view!!.context, "fetching data... ${newPage}", Toast.LENGTH_SHORT).show()
            tmdbViewModel.fetchMovies(newPage)
        }

    }

}
