package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.network

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.MovieData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.getSimple
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.RemoteData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.ErrorCode
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.vo.LoadingStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.*
import javax.inject.Inject

class RemoteDataImpl @Inject constructor(private val tmdbService: TmdbService) :
    RemoteData {
    override fun fetchItems(
        fetchDate: Date,
        onSuccess: (movies: List<MovieData>) -> Unit,
        onStatus: (status: LoadingStatus) -> Unit
    ) {
        val call = tmdbService.getMovies(fetchDate.getSimple())
        call.enqueue(object : Callback<List<MovieData>?> {
            override fun onResponse(call: Call<List<MovieData>?>?, response: Response<List<MovieData>?>?) {
                if (response != null) {
                    if (response.body()?.size == 0) {
                        onStatus(
                            LoadingStatus.error(
                            ErrorCode.NO_DATA))
                    } else {
                        response.body()?.let {
                            onSuccess(it)
                            Timber.d("fetchMore saved: %s", fetchDate)
                        }
                        onStatus(LoadingStatus.success())
                    }
                }
            }

            override fun onFailure(call: Call<List<MovieData>?>?, t: Throwable?) {
                if (t is IOException) {
                    onStatus(
                        LoadingStatus.error(
                        ErrorCode.NETWORK_ERROR, t.message))
                } else {
                    onStatus(
                        LoadingStatus.error(
                        ErrorCode.UNKNOWN_ERROR, null))
                }
            }
        })
    }
}