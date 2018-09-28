package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY release_date DESC, title ASC")
    fun loadMovies(): DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun truncateMovieTable()
}