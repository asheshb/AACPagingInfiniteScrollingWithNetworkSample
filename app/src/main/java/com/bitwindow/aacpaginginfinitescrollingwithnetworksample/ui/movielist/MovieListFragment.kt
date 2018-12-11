package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.movielist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.MovieSampleApp
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.R
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.ErrorCode
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.Status
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.toast
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


class MovieListFragment : Fragment() {
    private lateinit var movieListAdapter: MovieListAdapter
    @Inject
    lateinit var movieListViewModelFactory: MovieListViewModelFactory

    companion object {
        private const val COL = 2
    }

    override fun onAttach(context: Context?) {
        MovieSampleApp.instance.getApplicationComponent().plusFragmentComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movieListViewModel = ViewModelProviders.of(
            this, movieListViewModelFactory)
            .get(MovieListViewModel::class.java)


        movieListAdapter = MovieListAdapter {
            NavHostFragment.findNavController(this)
                .navigate(MovieListFragmentDirections.actionNext(it.toString()))
        }

        movieList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, COL)
            adapter = movieListAdapter
        }
        movieListViewModel.movies.observe(viewLifecycleOwner, Observer { list ->
            movieListAdapter.submitList(list)
        })

        movieListViewModel.loadingStatus.observe(viewLifecycleOwner, Observer { loadingStatus ->
            when {
                loadingStatus?.status == Status.LOADING -> loadingProgress.visibility = View.VISIBLE
                loadingStatus?.status == Status.SUCCESS -> {
                    loadingProgress.visibility = View.INVISIBLE
                    toggleRefreshing(false)
                }
                loadingStatus?.status == Status.ERROR -> {
                    loadingProgress.visibility = View.INVISIBLE
                    toggleRefreshing(false)
                    showErrorMessage(loadingStatus.errorCode, loadingStatus.message)
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            movieListViewModel.refresh()
        }
    }

    private fun toggleRefreshing(refreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = refreshing
    }

    private fun showErrorMessage(errorCode: ErrorCode?, message: String?) {
        when (errorCode) {
            ErrorCode.NO_DATA -> activity!!.toast(getString(R.string.error_no_data))
            ErrorCode.NETWORK_ERROR -> activity!!.toast(getString(R.string.error_network, message))
            ErrorCode.UNKNOWN_ERROR -> activity!!.toast(getString(R.string.error_unknown, message))
        }
    }
}
