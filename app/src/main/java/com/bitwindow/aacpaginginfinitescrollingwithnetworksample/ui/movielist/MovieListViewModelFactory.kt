/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.movielist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.movielist.MovieListUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 * Factory for ViewModels
 */
class MovieListViewModelFactory @Inject constructor(private val movieListUseCase: MovieListUseCase) : ViewModelProvider.Factory {
    init{
        Timber.d("init")
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(movieListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}