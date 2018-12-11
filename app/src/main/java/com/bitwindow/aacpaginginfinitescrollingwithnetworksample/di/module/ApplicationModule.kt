package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.TimberLogger
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.scope.ApplicationScope
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.Logger
import dagger.Binds
import dagger.Module

@Module(includes = [ApplicationModule.LoggerModule::class])
class ApplicationModule() {
    @Module
    interface LoggerModule{
        @Binds
        @ApplicationScope
        fun bindLogger(loagger : TimberLogger) : Logger
    }
}