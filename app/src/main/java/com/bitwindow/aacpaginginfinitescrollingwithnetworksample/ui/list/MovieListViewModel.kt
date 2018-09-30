package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
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

    private val boundaryCallback = MovieBoundaryCallback()

    val loadingStatus : LiveData<LoadingStatus> = Transformations.switchMap(boundaryCallback.boundaryState, {
        onBoundaryItemLoaded(it.itemData, it.direction)})

    val movies = getMovieSource()


    private fun getMovieSource(): LiveData<PagedList<Movie>> {
        val dataSourceFactory = movieRepository.getMovieDataSourceFactory()

        return LivePagedListBuilder(dataSourceFactory, MovieBoundaryCallback.DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    private fun onBoundaryItemLoaded(itemDate: Date, direction: Direction) : LiveData<LoadingStatus> {
        Timber.d("onBoundaryItemLoaded %s %s ", itemDate, direction)

        val fetchDate = when (direction) {
            Direction.BOTTOM -> Util.addDay(itemDate, -1)
            Direction.TOP -> Util.addDay(itemDate, +1)
            else -> itemDate
        }
        return movieRepository.fetchMore(fetchDate)
    }

    fun refresh() {
        Timber.d("refreshing")
        boundaryCallback.refresh()
    }
}