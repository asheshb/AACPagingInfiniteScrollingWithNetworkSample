package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.moviedetail

import android.arch.lifecycle.LiveData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie

class MovieDetailUseCase(private val repository: MovieDetailRepository){
    fun getMovie(id : Long): LiveData<Movie> {
        return repository.getMovie(id)
    }
}