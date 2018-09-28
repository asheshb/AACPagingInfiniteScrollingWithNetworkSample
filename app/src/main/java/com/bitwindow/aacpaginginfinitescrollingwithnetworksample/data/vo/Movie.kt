package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    val id: Long = 0,
    val title: String,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat(),
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val adult: Boolean,
    val overview: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: Date
)
