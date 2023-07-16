package com.rayliu.gymnote.wearos

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.navigation.composable
import com.rayliu.gymnote.wearos.addrecord.AddRecordScreen
import com.rayliu.gymnote.wearos.addrecord.AddRecordViewModel
import com.rayliu.gymnote.wearos.addrecord.recordinput.DEFAULT_TIME_RECORD
import com.rayliu.gymnote.wearos.categorylist.CategoryListScreen
import com.rayliu.gymnote.wearos.categorylist.CategoryListViewModel
import com.rayliu.gymnote.wearos.navigation.CATEGORY_ID_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.DestinationScrollType
import com.rayliu.gymnote.wearos.navigation.SCROLL_TYPE_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.Screen
import com.rayliu.gymnote.wearos.navigation.TIME_PICKER_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.TIME_PICKER_RESULT_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.WORKOUT_ID_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.timepicker.TimePickerScreen
import com.rayliu.gymnote.wearos.workout.WorkoutScreen
import com.rayliu.gymnote.wearos.workout.WorkoutViewModel
import com.rayliu.gymnote.wearos.workoutlist.WorkoutListScreen
import com.rayliu.gymnote.wearos.workoutlist.WorkoutListViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.toLocalTime
import org.koin.androidx.compose.navigation.koinNavViewModel

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(
        route = Screen.SportsCategory.route,
        arguments = listOf(
            navArgument(SCROLL_TYPE_NAV_ARGUMENT) {
                type = NavType.EnumType(DestinationScrollType::class.java)
                defaultValue = DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING
            }
        )
    ) { entry ->
        val focusRequester = remember { FocusRequester() }
        val viewModel: CategoryListViewModel = koinNavViewModel()
        val sportsCategories =
            viewModel.categoryListState.collectAsState().value
        val scalingLazyListState = scalingLazyListState(entry)
        LaunchedEffect(key1 = null) {
            viewModel.performPreScreenTasks()
        }

        CategoryListScreen(
            showLoadingScreen = viewModel.showProgress.value,
            sportCategories = sportsCategories,
            listState = scalingLazyListState,
            focusRequester = focusRequester,
            onCategoryClicked = {
                navController.navigate(Screen.WorkoutList.withArguments(it.id.toString()))
            },
            modifier = modifier
        )
        RequestFocusOnResume(focusRequester)
    }
    composable(
        route = Screen.WorkoutList.route + "/{$CATEGORY_ID_NAV_ARGUMENT}",
        arguments = listOf(
            navArgument(SCROLL_TYPE_NAV_ARGUMENT) {
                type = NavType.EnumType(DestinationScrollType::class.java)
                defaultValue = DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING
            },
            navArgument(CATEGORY_ID_NAV_ARGUMENT) {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            }
        )
    ) { entry ->
        val scalingLazyListState = scalingLazyListState(entry)
        val focusRequester = remember { FocusRequester() }
        val viewModel: WorkoutListViewModel = koinNavViewModel()
        val workoutInfos =
            viewModel.workoutInfoState.collectAsState().value

        WorkoutListScreen(
            workoutInfos = workoutInfos,
            listState = scalingLazyListState,
            focusRequester = focusRequester,
            onWorkoutClicked = {
                navController.navigate(Screen.Workout.withArguments(it.id.toString()))
            },
            modifier = modifier
        )
        RequestFocusOnResume(focusRequester)
    }
    composable(
        route = Screen.Workout.route + "/{$WORKOUT_ID_NAV_ARGUMENT}",
        arguments = listOf(
            navArgument(SCROLL_TYPE_NAV_ARGUMENT) {
                type = NavType.EnumType(DestinationScrollType::class.java)
                defaultValue = DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING
            },
            navArgument(WORKOUT_ID_NAV_ARGUMENT) {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            }
        )
    ) { entry ->
        val scalingLazyListState = scalingLazyListState(entry)
        val focusRequester = remember { FocusRequester() }
        val viewModel: WorkoutViewModel = koinNavViewModel()
        viewModel.performPreScreenTasks()
        val records = viewModel.workoutRecords.collectAsState(initial = persistentListOf()).value

        val context = LocalContext.current
        WorkoutScreen(
            listState = scalingLazyListState,
            focusRequester = focusRequester,
            workoutInfo = viewModel.workoutInfo.value,
            records = records,
            onAddButtonClicked = {
                navController.navigate(Screen.AddWorkoutRecord.withArguments(it.id.toString()))
            },
            onCardClicked = {
                Toast.makeText(
                    context,
                    "Record id is ${it.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        RequestFocusOnResume(focusRequester)
    }
    composable(
        route = Screen.AddWorkoutRecord.route + "/{$WORKOUT_ID_NAV_ARGUMENT}",
        arguments = listOf(
            navArgument(SCROLL_TYPE_NAV_ARGUMENT) {
                type = NavType.EnumType(DestinationScrollType::class.java)
                defaultValue = DestinationScrollType.NONE
            },
            navArgument(WORKOUT_ID_NAV_ARGUMENT) {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            }
        )
    ) { entry ->
        val focusRequester = remember { FocusRequester() }
        val viewModel: AddRecordViewModel = koinNavViewModel()
        viewModel.performPreScreenTasks()

        val recordTypes = viewModel.recordInputTypes.value
        val userInputTimeRecord =
            entry.savedStateHandle.get<String>(TIME_PICKER_RESULT_NAV_ARGUMENT)
        viewModel.setTimeRecord(userInputTimeRecord)
        val timeRecord = viewModel.timeRecord.value ?: DEFAULT_TIME_RECORD

        AddRecordScreen(
            recordTypes = recordTypes,
            focusRequester = focusRequester,
            onRequestFocus = {
                RequestFocusOnResume(it)
            },
            onCancelButtonClicked = {
                navController.popBackStack()
            },
            onTimeAdjustButtonClicked = {
                navController.navigate(
                    Screen.TimePickerScreen.route + "?$TIME_PICKER_NAV_ARGUMENT=$timeRecord"
                )
            },
            userInputTimeRecord = timeRecord
        )
    }
    composable(
        route = Screen.TimePickerScreen.route +
            "?$TIME_PICKER_NAV_ARGUMENT={$TIME_PICKER_NAV_ARGUMENT}",
        arguments = listOf(
            navArgument(SCROLL_TYPE_NAV_ARGUMENT) {
                type = NavType.EnumType(DestinationScrollType::class.java)
                defaultValue = DestinationScrollType.NONE
            },
            navArgument(TIME_PICKER_NAV_ARGUMENT) {
                type = NavType.StringType
                defaultValue = DEFAULT_TIME_RECORD
                nullable = true
            }
        )
    ) { entry ->
        val focusRequester = remember { FocusRequester() }
        val defaultTime = entry.arguments?.getString(TIME_PICKER_NAV_ARGUMENT)
        TimePickerScreen(
            focusRequester = focusRequester,
            onTimeConfirm = { resultTime ->
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(TIME_PICKER_RESULT_NAV_ARGUMENT, resultTime)

                navController.popBackStack()
            },
            defaultTime = defaultTime?.toLocalTime()
        )
        RequestFocusOnResume(focusRequester)
    }
}

@SuppressLint("ComposeViewModelInjection")
@Composable
fun scalingLazyListState(
    navBackStackEntry: NavBackStackEntry
): ScalingLazyListState {
    val passedScrollType = navBackStackEntry.arguments?.getSerializable(SCROLL_TYPE_NAV_ARGUMENT)
    check(
        passedScrollType == DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING
    ) {
        "Scroll type must be DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING"
    }
    val scrollViewModel: ScalingLazyListStateViewModel = viewModel(navBackStackEntry)
    return scrollViewModel.scrollState
}

@Composable
private fun RequestFocusOnResume(focusRequester: FocusRequester) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
            focusRequester.requestFocus()
        }
    }
}
