package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.MovieData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.MoviePoster

@Dao
interface MovieDao {

    @Query("SELECT id, title, posterPath, releaseDate, voteAverage  FROM movies ORDER BY releaseDate DESC, title ASC")
    fun getDataSourcefactory(): DataSource.Factory<Int, MoviePoster>

    @Query("SELECT * FROM movies WHERE id= :id")
    fun getMovie(id: Long) : LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieData>)

    @Query("DELETE FROM movies")
    fun deleteMovies()
}