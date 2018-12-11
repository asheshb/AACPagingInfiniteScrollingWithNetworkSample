package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.component

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module.DataModule
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module.DomainModule
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.scope.FragmentScope
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.moviedetail.MovieDetailFragment
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.movielist.MovieListFragment
import dagger.Subcomponent

@Subcomponent(modules = [DataModule::class, DomainModule::class])
@FragmentScope
interface FragmentComponent{
    fun inject(movieListFragment: MovieListFragment)
    fun inject(movieDetailFragment: MovieDetailFragment)
}