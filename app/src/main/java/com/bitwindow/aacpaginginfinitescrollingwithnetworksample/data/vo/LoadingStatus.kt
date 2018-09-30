package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

enum class ErrorCode {
    NO_DATA,
    NETWORK_ERROR,
    UNKNOWN_ERROR
}

data class LoadingStatus(val status: Status, val errorCode: ErrorCode?, val message: String?) {
    companion object {
        fun loading(): LoadingStatus {
            return LoadingStatus(Status.LOADING, null, null)
        }

        fun success(errorCode: ErrorCode? = null, msg: String? = null): LoadingStatus {
            return LoadingStatus(Status.SUCCESS, errorCode, msg)
        }

        fun error(errorCode: ErrorCode, msg: String? = null): LoadingStatus {
            return LoadingStatus(Status.ERROR, errorCode, msg)
        }
    }
}