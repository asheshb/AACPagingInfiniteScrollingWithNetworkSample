package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.utility

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class Util {

    companion object {

        fun getReadableDate(date: Date?): String {
            val dateFormat = SimpleDateFormat("dd MMM yy", Locale.getDefault())
            return dateFormat.format(date)
        }

        fun getSimpleDate(date: Date?): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return if (date != null) {
                dateFormat.format(date)
            } else {
                dateFormat.format(Date())
            }
        }

        fun dateDiff(date1: Date, date2: Date): Int {
            val diff: Long = date1.time - date2.time
            return (diff / 86400000).toInt()
        }

        fun addDay(date: Date, days: Int): Date {
            val c = Calendar.getInstance()
            c.time = date
            c.add(Calendar.DAY_OF_YEAR, days)
            return c.time
        }

        inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(vClass: Class<T>): T = f() as T
            }

    }
}