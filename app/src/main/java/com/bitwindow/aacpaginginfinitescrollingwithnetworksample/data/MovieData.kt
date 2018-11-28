package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

// This class is counterpart of Movie entity in domain layer. This class is used by Room as well
// as retrofit to parse fetched data. We could have further created classes for Room
// and network parsing to separate the concerns. Right now we are mapping the database and network
// class exactly so I have left it like this.
@Entity(tableName = "movies")
data class MovieData (
    @PrimaryKey
    val id: Long = 0,

    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.toFloat(),

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    val adult: Boolean,

    val overview: String?,

    @SerializedName("release_date")
    val releaseDate: Date
)
