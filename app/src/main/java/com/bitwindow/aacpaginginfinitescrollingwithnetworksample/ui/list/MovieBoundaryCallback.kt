package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.list

import android.arch.paging.PagedList
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Direction
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Movie
import timber.log.Timber
import java.util.*

class MovieBoundaryCallback(
    private val listener: (Date, Direction) -> Unit
) : PagedList.BoundaryCallback<Movie>() {

    companion object {
        const val DATABASE_PAGE_SIZE = 15
    }

    override fun onItemAtFrontLoaded(itemAtFront: Movie) {
        Timber.d(
            "onItemAtFrontLoaded %d %s %s ", itemAtFront.id,
            itemAtFront.title, itemAtFront.hashCode()
        )
        listener(itemAtFront.releaseDate, Direction.TOP)
    }

    override fun onZeroItemsLoaded() {
        Timber.d("onZeroItemsLoaded")
        listener(Date(), Direction.NONE)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        Timber.d(
            "onItemAtFrontLoaded %d %s %s ", itemAtEnd.id,
            itemAtEnd.title, itemAtEnd.hashCode()
        )
        listener(itemAtEnd.releaseDate, Direction.BOTTOM)
    }
}