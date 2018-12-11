package com.bitwindow.aacpaginginfinitescrollingwithnetworksample

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.Logger
import timber.log.Timber
import javax.inject.Inject

//Currently used only by "domain" layer classes to remove Timber dependency in domain layer
class TimberLogger @Inject constructor() : Logger{

    companion object {
        @Volatile
        private var INSTANCE: TimberLogger? = null

        fun getInstance(): TimberLogger =
            INSTANCE
                    ?: synchronized(this) {
                        INSTANCE
                                ?: buildInstance().also { INSTANCE = it }
                    }

        private fun buildInstance() = TimberLogger()
    }

    override fun d(msg : String, vararg values: Any?){
        Timber.d(msg, values)
    }

}