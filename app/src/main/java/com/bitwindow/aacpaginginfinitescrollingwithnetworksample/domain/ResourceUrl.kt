package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain

class ResourceUrl {
    companion object {
        private const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        private const val backdropBaseUrl = "https://image.tmdb.org/t/p/w300"

        fun getPosterUrl(id :String) = posterBaseUrl + id

        fun getBackdropUrl(id :String) : String = backdropBaseUrl + id
    }
}