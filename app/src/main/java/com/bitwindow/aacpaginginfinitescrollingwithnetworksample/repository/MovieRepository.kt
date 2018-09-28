package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.repository

import android.arch.paging.DataSource
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.MovieDao
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.ErrorCode
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.LoadingStatus
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.network.TmdbService
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.utility.AppExecutors
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.utility.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.*


class MovieRepository private constructor(
    private val appExecutors: AppExecutors,
    private val movieDao: MovieDao,
    private val tmdbService: TmdbService
) {

    private var loading = false

    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(
            appExecutors: AppExecutors,
            movieDao: MovieDao,
            tmdbService: TmdbService
        ): MovieRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: (MovieRepository(appExecutors, movieDao, tmdbService)).also {
                    INSTANCE = it
                }
            }
    }

    fun getMovieDataSourceFactory(): DataSource.Factory<Int, Movie> {
        return movieDao.loadMovies()
    }

    fun fetchMore(fetchDate: Date, loadingStatusListener: (LoadingStatus) -> Unit) {
        loadingStatusListener(LoadingStatus.loading())
        if (loading) {
            Timber.d("fetchMore already loading %s", fetchDate)
            return
        }
        val dateDiff = Util.dateDiff(fetchDate, Date())
        if (dateDiff > 0) {
            loadingStatusListener(LoadingStatus.success())
            Timber.d("fetchMore future date %s", fetchDate)
            return
        }
        loading = true
        Timber.d("fetchMore starting: %s", fetchDate)

        fetchItems(fetchDate, loadingStatusListener)
    }

    private fun fetchItems(fetchDate: Date, loadingStatusListener: (LoadingStatus) -> Unit) {
        val call = tmdbService.getMovies(Util.getSimpleDate(fetchDate))
        call.enqueue(object : Callback<List<Movie>?> {
            override fun onResponse(call: Call<List<Movie>?>?, response: Response<List<Movie>?>?) {
                if (response != null) {
                    if (response.body()?.size == 0 && Util.dateDiff(fetchDate, Date()) < 0) {
                        loadingStatusListener(LoadingStatus.error(ErrorCode.NO_DATA))
                    } else {
                        appExecutors.diskIO().execute {
                            response.body()?.let {
                                movieDao.insertMovies(it.filter { m -> m.posterPath != null })
                                Timber.d("fetchMore saved: %s", fetchDate)
                            }
                        }
                        loadingStatusListener(LoadingStatus.success())
                    }
                }
                loading = false
            }

            override fun onFailure(call: Call<List<Movie>?>?, t: Throwable?) {
                if (t is IOException) {
                    loadingStatusListener(LoadingStatus.error(ErrorCode.NETWORK_ERROR, t.message))
                } else {
                    loadingStatusListener(LoadingStatus.error(ErrorCode.UNKNOWN_ERROR, null))
                }
                loading = false
            }
        })
    }
}
