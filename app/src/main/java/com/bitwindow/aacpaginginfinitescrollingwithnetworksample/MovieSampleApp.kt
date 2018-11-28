/*
 * Copyright (C) 2018 Ashesh Bharadwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bitwindow.aacpaginginfinitescrollingwithnetworksample

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.LocalData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.MovieDao
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.MovieDb
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.network.RemoteData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.network.TmdbService
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.LocalDataSource
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.MovieDetailDataRepository
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.MovieListDataRepository
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.RemoteDataSource
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.moviedetail.MovieDetailRepository
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.moviedetail.MovieDetailUseCase
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.movielist.MovieListRepository
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.movielist.MovieListUseCase
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.moviedetail.MovieDetailViewModelFactory
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.movielist.MovieListViewModelFactory
import timber.log.Timber

class MovieSampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(PrefixDebugTree())
        }
    }

    companion object {
        lateinit var instance: MovieSampleApp
            private set
    }

    // provide dependency objects
    fun provideMovieListViewModelFactory() : ViewModelProvider.Factory{
        return MovieListViewModelFactory(provideMovieListUseCase())
    }

    fun provideMovieDetailViewModelFactory() : ViewModelProvider.Factory{
        return MovieDetailViewModelFactory(provideMovieDetailUseCase())
    }

    private fun provideMovieListUseCase(): MovieListUseCase {
        return MovieListUseCase(provideMovieListRepository(), provideLogger())
    }

    private fun provideMovieListRepository(): MovieListRepository {
        return MovieListDataRepository(provideAppExecutors(), provideLocalData(), provideRemoteData())
    }

    private fun provideMovieDetailUseCase(): MovieDetailUseCase {
        return MovieDetailUseCase(provideMovieDetailRepository())
    }

    private fun provideMovieDetailRepository(): MovieDetailRepository {
        return MovieDetailDataRepository(provideLocalData())
    }

    private fun provideDb(): MovieDb {
        return MovieDb.getInstance(this)
    }

    private fun provideMovieDao(): MovieDao {
        return provideDb().movieDao()
    }

    private fun provideTmdbService(): TmdbService {
        return TmdbService.getInstance()
    }

    private fun provideLocalData(): LocalDataSource {
        return LocalData(provideAppExecutors(), provideMovieDao())
    }

    private fun provideRemoteData(): RemoteDataSource {
        return RemoteData(provideTmdbService())
    }

    private fun provideAppExecutors(): AppExecutors {
        return AppExecutors.getInstance()
    }

    private fun provideLogger(): TimberLogger {
        return TimberLogger.getInstance()
    }
}
