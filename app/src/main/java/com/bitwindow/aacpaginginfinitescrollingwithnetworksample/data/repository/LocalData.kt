package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.MovieData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.MoviePoster
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.BoundaryState
import java.util.*

// This interface is in accordance to Dependency Inversion Principle by separating the higher
// repository class from lower database class.
interface LocalData {

    fun getMovies() : LiveData<PagedList<MoviePoster>>
    fun getBoundaryState(): LiveData<BoundaryState<Date>>
    fun insertMovies(movies: List<MovieData>)
    fun deleteMovies()
    fun refresh()

    fun getMovie(id : Long) : LiveData<Movie>
}