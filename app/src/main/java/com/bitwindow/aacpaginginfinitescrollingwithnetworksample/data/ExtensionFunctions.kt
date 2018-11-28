package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data

import java.text.SimpleDateFormat
import java.util.*

fun Date.getSimple(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(this)
}