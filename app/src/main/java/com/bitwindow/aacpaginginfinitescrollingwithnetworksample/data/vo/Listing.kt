package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

data class Listing<T>(
    val pagedList : LiveData<PagedList<T>>,
    val loadingStatus : LiveData<LoadingStatus>
)