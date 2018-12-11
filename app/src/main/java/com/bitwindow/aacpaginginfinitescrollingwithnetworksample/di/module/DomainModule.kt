package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.MovieDetailRepositoryImpl
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.MovieListRepositoryImpl
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.scope.FragmentScope
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.moviedetail.MovieDetailRepository
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.movielist.MovieListRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule{

    @Binds
    @FragmentScope
    fun bindMovieListRepository(movieListRepository : MovieListRepositoryImpl) : MovieListRepository

    @Binds
    @FragmentScope
    fun bindMovieDetailRepository(movieDetailRepository : MovieDetailRepositoryImpl) : MovieDetailRepository
}