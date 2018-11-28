package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository

import android.arch.lifecycle.LiveData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.moviedetail.MovieDetailRepository


class MovieDetailDataRepository (
    private val localData: LocalDataSource
) : MovieDetailRepository {

    override
    fun getMovie(id : Long): LiveData<Movie> {
        return localData.getMovie(id)
    }
}
