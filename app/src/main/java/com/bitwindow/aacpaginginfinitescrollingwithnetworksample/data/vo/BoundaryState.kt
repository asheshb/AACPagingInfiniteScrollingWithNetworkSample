package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo

enum class Direction {
    NONE,
    TOP,
    BOTTOM
}

data class BoundaryState<T>(val itemData : T, val direction: Direction) {
    companion object {
        fun <T> zeroItemsLoaded(itemData : T): BoundaryState<T> {
            return BoundaryState(itemData, Direction.NONE)
        }

        fun <T> itemLoadedAtTop(itemData : T): BoundaryState <T> {
            return BoundaryState(itemData, Direction.TOP)
        }

        fun <T> itemLoadedAtBottom(itemData : T): BoundaryState <T> {
            return BoundaryState(itemData, Direction.BOTTOM)
        }
    }
}