package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.MovieSampleApp
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Direction
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.LoadingStatus
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.repository.MovieRepository
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.utility.Util
import timber.log.Timber
import java.util.*

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MovieRepository =
        getApplication<MovieSampleApp>().getMovieRepository()

    private var movieSource: LiveData<PagedList<Movie>>
    private val _movies = MediatorLiveData<PagedList<Movie>>()
    val movies: LiveData<PagedList<Movie>>
        get() = _movies

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    init {
        movieSource = getMovieSource()
        _movies.addSource(movieSource) { _movies.value = it }
    }

    private fun getMovieSource(): LiveData<PagedList<Movie>> {
        val dataSourceFactory = movieRepository.getMovieDataSourceFactory()
        val boundaryCallback = MovieBoundaryCallback(this::onBoundaryItemLoaded)

        return LivePagedListBuilder(dataSourceFactory, MovieBoundaryCallback.DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    private fun onBoundaryItemLoaded(itemDate: Date, direction: Direction) {
        Timber.d("onBoundaryItemLoaded %s %s ", itemDate, direction)

        val fetchDate = when (direction) {
            Direction.BOTTOM -> Util.addDay(itemDate, -1)
            Direction.TOP -> Util.addDay(itemDate, +1)
            else -> itemDate
        }
        movieRepository.fetchMore(fetchDate, this::updateLoadingStatus)
    }

    private fun updateLoadingStatus(loadingStatus: LoadingStatus) {
        this._loadingStatus.value = loadingStatus
    }

    // After re adding the source, onItemAtFrontLoaded() of MovieBoundaryCallback class should
    // have been called in  but that's not happening
    fun refresh() {
        Timber.d("refreshing")
        _movies.removeSource(movieSource)
        movieSource = getMovieSource()
        _movies.addSource(movieSource) {
            _movies.value = it
        }
    }
}