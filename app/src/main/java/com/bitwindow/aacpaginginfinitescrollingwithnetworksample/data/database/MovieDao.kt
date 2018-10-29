package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.repository.DaoInterface

@Dao
interface MovieDao : DaoInterface {

    @Query("SELECT * FROM movies ORDER BY release_date DESC, title ASC")
    override fun loadMovies(): DataSource.Factory<Int, Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertMovies(movies: List<Movie>)


    @Query("DELETE FROM movies")
    override fun truncateMovieTable()
}