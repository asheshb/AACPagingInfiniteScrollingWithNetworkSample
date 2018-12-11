package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.MovieDb
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DbModule(){

    @Provides
    @ApplicationScope
    fun provideMovieDb(application: Application) =
        Room.databaseBuilder(
            application,
            MovieDb::class.java, "movie.db"
        ).build()

    @Provides
    @ApplicationScope
    fun provideMovieDao(movieDb: MovieDb) = movieDb.movieDao()
}