package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.utility

import timber.log.Timber

class PrefixDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        //Add `DroidLog` prefix to all logs so that they can be easily filtered in Logcat
        return "DroidLog " + super.createStackElementTag(element) + ":" + element.lineNumber
    }
}