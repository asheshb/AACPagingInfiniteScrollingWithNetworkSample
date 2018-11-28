package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain

import java.util.*

fun Date.diffDays(date: Date): Int {
    val diff: Long = this.time - date.time
    return (diff / 86400000).toInt()
}

fun Date.addDays(days: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DAY_OF_YEAR, days)
    return c.time
}