package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.component

import android.app.Application
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module.ApplicationModule
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module.DbModule
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.module.RetrofitModule
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component



@Component(modules = [ApplicationModule::class, DbModule::class, RetrofitModule::class])
@ApplicationScope
interface ApplicationComponent{
    fun plusFragmentComponent() : FragmentComponent


    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}