package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.repository

import android.arch.paging.DataSource
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Movie

interface DaoInterface{
    fun loadMovies(): DataSource.Factory<Int, Movie>

    fun insertMovies(movies: List<Movie>)

    fun truncateMovieTable()
}