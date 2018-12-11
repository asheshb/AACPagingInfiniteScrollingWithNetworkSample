package com.bitwindow.aacpaginginfinitescrollingwithnetworksample

import timber.log.Timber

class PrefixDebugTree : Timber.DebugTree() {
    private val CALL_STACK_INDEX = 8

    override fun createStackElementTag(element: StackTraceElement): String? {

        //Add `DroidLog` prefix to all logs so that they can be easily filtered in Logcat
        return "DroidLog " + super.createStackElementTag(getOriginalStackTraceElement(element)) + ":" + element.lineNumber
    }

    private fun getOriginalStackTraceElement(element: StackTraceElement): StackTraceElement {
        //if called from the interface ge the actual class which called to log
        if(element.fileName.equals("TimberLogger.kt")){
            val elements = Throwable()
                .stackTrace
            return elements[CALL_STACK_INDEX]
        }         else{
            return element
        }
    }
}