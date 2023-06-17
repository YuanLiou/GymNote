package com.rayliu.gymnote.wearos

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
import com.rayliu.gymnote.wearos.categorylist.CategoryListScreen
import com.rayliu.gymnote.wearos.categorylist.CategoryListViewModel
import com.rayliu.gymnote.wearos.navigation.CATEGORY_ID_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.DestinationScrollType
import com.rayliu.gymnote.wearos.navigation.SCROLL_TYPE_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.Screen
import com.rayliu.gymnote.wearos.workoutlist.WorkoutListScreen
import com.rayliu.gymnote.wearos.workoutlist.WorkoutListViewModel
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

        val context = LocalContext.current
        WorkoutListScreen(
            workoutInfos = workoutInfos,
            listState = scalingLazyListState,
            focusRequester = focusRequester,
            onWorkoutClicked = {
                Toast.makeText(context, "workout clicked ${it.name}", Toast.LENGTH_SHORT).show()
            },
            modifier = modifier
        )
        RequestFocusOnResume(focusRequester)
    }
}

@Composable
fun scalingLazyListState(it: NavBackStackEntry): ScalingLazyListState {
    val passedScrollType = it.arguments?.getSerializable(SCROLL_TYPE_NAV_ARGUMENT)
    check(
        passedScrollType == DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING
    ) {
        "Scroll type must be DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING"
    }
    val scrollViewModel: ScalingLazyListStateViewModel = viewModel(it)
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
