package com.rayliu.gymnote.wearos

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.scrollAway
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.rayliu.gymnote.wearos.navigation.DestinationScrollType
import com.rayliu.gymnote.wearos.navigation.SCROLL_TYPE_NAV_ARGUMENT
import com.rayliu.gymnote.wearos.navigation.Screen
import com.rayliu.gymnote.wearos.theme.GymNoteTheme

@SuppressLint("ComposeViewModelInjection")
@Composable
fun WearApp(
    modifier: Modifier = Modifier,
    swipeDismissableNavController: NavHostController = rememberSwipeDismissableNavController()
) {
    GymNoteTheme {
        val vignetteVisiblePreference by rememberSaveable { mutableStateOf(true) }
        val currentBackStackEntry by swipeDismissableNavController.currentBackStackEntryAsState()
        val scrollType =
            currentBackStackEntry?.arguments?.getSerializable(SCROLL_TYPE_NAV_ARGUMENT)
                ?: DestinationScrollType.NONE

        Scaffold(
            timeText = {
                currentBackStackEntry?.let { backStackEntry ->
                    val scrollViewModel: ScalingLazyListStateViewModel =
                        viewModel(backStackEntry)
                    val timeTextModifier =
                        if (scrollType == DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING) {
                            Modifier.scrollAway(scrollViewModel.scrollState)
                        } else {
                            null
                        }

                    key(backStackEntry.destination.route) {
                        TimeText(
                            modifier = timeTextModifier ?: Modifier
                        )
                    }
                }
            },
            vignette = {
                if (scrollType == DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING &&
                    vignetteVisiblePreference
                ) {
                    Vignette(vignettePosition = VignettePosition.TopAndBottom)
                }
            },
            positionIndicator = {
                currentBackStackEntry?.let { backStackEntry ->
                    if (scrollType == DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING) {
                        // Get or create the ViewModel associated with the current back stack entry
                        val scrollViewModel: ScalingLazyListStateViewModel =
                            viewModel(backStackEntry)
                        PositionIndicator(scalingLazyListState = scrollViewModel.scrollState)
                    }
                }
            }
        ) {
            SwipeDismissableNavHost(
                navController = swipeDismissableNavController,
                startDestination = Screen.SportsCategory.route,
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {
                mainNavGraph(
                    navController = swipeDismissableNavController,
                    modifier = modifier,
                )
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun DefaultPreview() {
    WearApp()
}
