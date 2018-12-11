package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.moviedetail

import android.arch.lifecycle.LiveData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.Logger
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val repository: MovieDetailRepository, private val log : Logger){
    fun getMovie(id : Long): LiveData<Movie> {
        return repository.getMovie(id)
    }
}