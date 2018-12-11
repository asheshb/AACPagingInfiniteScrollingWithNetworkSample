package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.MovieData

@Database(
    entities = [
        MovieData::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DbTypeConverters::class)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}