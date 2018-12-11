package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository

import android.arch.lifecycle.LiveData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.moviedetail.MovieDetailRepository
import timber.log.Timber
import javax.inject.Inject


class MovieDetailRepositoryImpl @Inject constructor(
    private val localData: LocalData
) : MovieDetailRepository {
    override
    fun getMovie(id : Long): LiveData<Movie> {
        return localData.getMovie(id)
    }
}
