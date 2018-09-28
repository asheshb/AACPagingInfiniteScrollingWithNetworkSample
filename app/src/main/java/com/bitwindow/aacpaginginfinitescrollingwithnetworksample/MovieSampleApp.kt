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
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.MovieDao
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.MovieDb
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.network.TmdbService
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.repository.MovieRepository
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.utility.AppExecutors
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.utility.PrefixDebugTree
import timber.log.Timber

class MovieSampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(PrefixDebugTree())
        }
    }

    private fun getAppExecutors(): AppExecutors {
        return AppExecutors.getInstance()
    }

    private fun getDb(): MovieDb {
        return MovieDb.getInstance(this)
    }

    private fun getMovieDao(): MovieDao {
        return getDb().movieDao()
    }

    private fun getTmdbService(): TmdbService {
        return TmdbService.getInstance()
    }

    fun getMovieRepository(): MovieRepository {
        return MovieRepository.getInstance(getAppExecutors(), getMovieDao(), getTmdbService())
    }
}
