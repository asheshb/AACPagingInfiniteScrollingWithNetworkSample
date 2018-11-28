package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
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

    companion object {
        @Volatile
        private var INSTANCE: MovieDb? = null

        fun getInstance(context: Context): MovieDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDb::class.java, "movie.db"
            ).build()
    }
}