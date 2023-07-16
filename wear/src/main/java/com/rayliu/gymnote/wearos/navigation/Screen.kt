package com.rayliu.gymnote.wearos.navigation

const val SCROLL_TYPE_NAV_ARGUMENT = "scrollType"
const val CATEGORY_ID_NAV_ARGUMENT = "categoryId"
const val WORKOUT_ID_NAV_ARGUMENT = "workoutId"
const val TIME_PICKER_RESULT_NAV_ARGUMENT = "timePickerResult"

sealed class Screen(val route: String) {
    object SportsCategory : Screen("sports_category")
    object WorkoutList : Screen("workout_list")
    object Workout : Screen("workout")
    object AddWorkoutRecord : Screen("add_workout_record")
    object TimePickerScreen : Screen("time_picker_screen")

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
