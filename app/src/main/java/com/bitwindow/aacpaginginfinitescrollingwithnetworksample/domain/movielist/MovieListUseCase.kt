package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.movielist

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.Logger
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.addDays
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.diffDays
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.MoviePoster
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.BoundaryState
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.Direction
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.LoadingStatus
import java.util.*
import javax.inject.Inject

class MovieListUseCase @Inject constructor(private val repository: MovieListRepository, private val log : Logger){

    fun getMovies(): LiveData<PagedList<MoviePoster>> {
        return repository.getMovies()
    }

    fun getBoundaryState(): LiveData<BoundaryState<Date>> {
        return repository.getBoundaryState()
    }

    // Check which direction the event happened. If the user has scrolled to the top, the
    // direction will be TOP and if user has scrolled to the bottom (no more data in database)
    // then direction will be BOTTOM. If there is no data (usually first time the app start)
    // then fetch movies for current date
    fun fetchMore(itemDate: Date, direction: Direction) : LiveData<LoadingStatus> {
        val fetchDate = when (direction) {
            Direction.BOTTOM -> itemDate.addDays(-1)
            Direction.TOP -> itemDate.addDays(+1)
            else -> itemDate
        }

        val dateDiff = fetchDate.diffDays(Date())

        return if (dateDiff > 0) {
            log.d("fetchMore future date %s", fetchDate)
            //if it's a future date don't fetch movies. If repository is still loading some data
            // then return loading or success to hide the progress bar.
            repository.returnLoadingOrSuccess()
        } else{
            log.d("fetchMore starting: %s", fetchDate)
            // Discard a movie which doesn't have poster path because on our list UI we just
            // show posters
            repository.fetchMore(fetchDate){posterPath -> posterPath != null}
        }
    }

    fun refresh(){
        repository.refresh()
    }
}