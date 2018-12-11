package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.AppExecutors
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.MovieData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.LocalData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.MoviePoster
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.BoundaryState
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class LocalDataImpl @Inject constructor(private val appExecutors: AppExecutors,
                                        private val movieDao: MovieDao) :
    LocalData {
    private val boundaryCallback =
        MovieBoundaryCallback()

    override fun getMovies(): LiveData<PagedList<MoviePoster>> {
        val dataSourceFactory = movieDao.getDataSourcefactory()
        return LivePagedListBuilder(dataSourceFactory, MovieBoundaryCallback.DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    override fun getMovie(id : Long): LiveData<Movie> {
        return movieDao.getMovie(id)
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Date>> {
        return boundaryCallback.boundaryState
    }

    override fun insertMovies(movies: List<MovieData>) {
        appExecutors.diskIO().execute {
            movieDao.insertMovies(movies)
        }
    }

    override fun deleteMovies() {
        movieDao.deleteMovies()
    }

    override fun refresh() {
        boundaryCallback.refresh()
    }
}