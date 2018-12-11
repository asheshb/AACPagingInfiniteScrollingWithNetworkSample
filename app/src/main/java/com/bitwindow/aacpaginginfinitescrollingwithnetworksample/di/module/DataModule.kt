package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database.LocalDataImpl
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.network.RemoteDataImpl
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.LocalData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.repository.RemoteData
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface DataModule{

    @Binds
    @FragmentScope
    fun bindLocalData (localData : LocalDataImpl) : LocalData

    @Binds
    @FragmentScope
    fun bindRemoteData (remoteData : RemoteDataImpl) : RemoteData

}