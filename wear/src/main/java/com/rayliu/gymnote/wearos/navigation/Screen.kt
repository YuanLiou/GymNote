package com.rayliu.gymnote.wearos.navigation

const val SCROLL_TYPE_NAV_ARGUMENT = "scrollType"

sealed class Screen(val route: String) {
    object SportsCategory : Screen("sports_category")
    object WorkoutDetails : Screen("workout_details")
}

enum class DestinationScrollType {
    NONE,
    SCALING_LAZY_COLUMN_SCROLLING
}
