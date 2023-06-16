package com.rayliu.gymnote.wearos.navigation

const val SCROLL_TYPE_NAV_ARGUMENT = "scrollType"

sealed class Screen(val route: String) {
    object SportsCategory : Screen("sports_category")
    object WorkoutList : Screen("workout_list")

    fun withArguments(vararg arguments: String): String {
        return buildString {
            append(route)
            arguments.forEach { argument ->
                append("/$argument")
            }
        }
    }
}

enum class DestinationScrollType {
    NONE,
    SCALING_LAZY_COLUMN_SCROLLING
}
