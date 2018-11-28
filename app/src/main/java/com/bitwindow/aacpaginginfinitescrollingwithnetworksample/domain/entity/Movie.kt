package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity

import java.util.*

// The highest class without any dependency and representing the Movie entity around which this
// app is designed.
data class Movie (
    val id: Long = 0,
    val title: String,
    val voteAverage: Float = 0.toFloat(),
    val posterPath: String,
    val releaseDate: Date,
    val backdropPath: String?,
    val adult: Boolean,
    val overview: String?

)
