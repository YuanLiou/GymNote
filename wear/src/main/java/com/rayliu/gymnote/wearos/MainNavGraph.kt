package com.rayliu.gymnote.wearos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.wear.compose.navigation.composable
import androidx.navigation.navArgument
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.rayliu.gymnote.wearos.categorylist.CategoryListScreen
import com.rayliu.gymnote.wearos.categorylist.CategoryListViewModel
import com.rayliu.gymnote.wearos.navigation.DestinationScrollType
import com.rayliu.gymnote.wearos.navigation.SCROLL_TYPE_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.Screen
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.navigation.koinNavViewModel

fun NavGraphBuilder.mainNavGraph(
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
    ) {
        val viewModel: CategoryListViewModel = koinNavViewModel()
        val sportsCategories =
            viewModel.provideCategories().collectAsState(initial = emptyList()).value
        val scalingLazyListState = scalingLazyListState(it)
        LaunchedEffect(key1 = null) {
            viewModel.performPreScreenTasks()
        }

        CategoryListScreen(
            showLoadingScreen = viewModel.showProgress.value,
            sportCategories = sportsCategories.toImmutableList(),
            listState = scalingLazyListState,
            modifier = modifier
        )
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
