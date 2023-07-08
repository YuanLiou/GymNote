package com.rayliu.gymnote.wearos.addrecord

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.HorizontalPageIndicator
import com.google.android.horologist.compose.pager.PageScreenIndicatorState
import com.rayliu.gymnote.wearos.addrecord.recordinput.AddDistanceRecordScreen
import com.rayliu.gymnote.wearos.addrecord.recordinput.AddRecordActionsScreen
import com.rayliu.gymnote.wearos.addrecord.recordinput.AddRepsRecordScreen
import com.rayliu.gymnote.wearos.addrecord.recordinput.AddTimeRecordScreen
import com.rayliu.gymnote.wearos.addrecord.recordinput.AddWeightRecordScreen
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddRecordScreen(
    workoutId: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val pageCounts = 5
        val state = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            pageCounts
        }

        HorizontalPager(
            state = state,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> {
                    AddWeightRecordScreen(
                        modifier = Modifier
                    )
                }
                1 -> {
                    AddRepsRecordScreen(
                        modifier = Modifier
                    )
                }
                2 -> {
                    AddTimeRecordScreen(
                        modifier = Modifier
                    )
                }
                3 -> {
                    AddDistanceRecordScreen(
                        modifier = Modifier
                    )
                }
                4 -> {
                    val context  = LocalContext.current
                    AddRecordActionsScreen(
                        onOKButtonClicked = {
                            Toast.makeText(
                                context,
                                "Ok Pressed",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onCancelButtonClicked = {
                            Toast.makeText(
                                context,
                                "Cancel Pressed",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier
                    )
                }
            }
        }

        val pagerScreenState = remember { PageScreenIndicatorState(state) }
        HorizontalPageIndicator(
            pageIndicatorState = pagerScreenState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
    }
}

@Preview(
    group = "Screen Preview",
    widthDp = PreviewConstants.WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL
)
@Composable
private fun AddRecordScreenPreview() {
    GymNoteTheme {
        AddRecordScreen("123")
    }
}
