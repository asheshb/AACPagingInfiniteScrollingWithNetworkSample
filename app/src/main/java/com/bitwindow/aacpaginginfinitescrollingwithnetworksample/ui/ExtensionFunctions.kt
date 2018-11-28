package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui

import java.text.SimpleDateFormat
import java.util.*

fun Date.getReadable(): String {
    val dateFormat = SimpleDateFormat("dd MMM yy", Locale.getDefault())
    return dateFormat.format(this)
}

