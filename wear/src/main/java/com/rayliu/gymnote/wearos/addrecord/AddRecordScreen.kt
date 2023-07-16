package com.rayliu.gymnote.wearos.addrecord

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
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
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddRecordScreen(
    recordTypes: ImmutableSet<RecordType>,
    focusRequester: FocusRequester,
    onCancelButtonClicked: () -> Unit,
    onRequestFocus: @Composable (FocusRequester) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val pageCounts = recordTypes.size + 1
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
            for (recordType in recordTypes) {
                if (page == recordTypes.indexOf(recordType)) {
                    ShowScreenByRecordType(focusRequester, recordType)
                    onRequestFocus(focusRequester)
                }
            }

            if (page == pageCounts - 1) {
                val context = LocalContext.current
                FinalSubmitActions(
                    onOkButtonClicked = {
                        Toast.makeText(
                            context,
                            "Ok Pressed",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onCancelButtonClicked = onCancelButtonClicked
                )
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

@Composable
private fun ShowScreenByRecordType(
    focusRequester: FocusRequester,
    recordType: RecordType,
) {
    when (recordType) {
        RecordType.WEIGHT -> {
            val defaultText = "0.0"
            var userInput by remember { mutableStateOf(defaultText) }
            AddWeightRecordScreen(
                focusRequester = focusRequester,
                valueUnit = "kg",
                defaultText = defaultText,
                userInput = userInput,
                onInputChanged = { userInput = it },
                modifier = Modifier
            )
        }

        RecordType.REPS -> {
            val defaultText = "0"
            var userInput by remember { mutableStateOf(defaultText) }
            AddRepsRecordScreen(
                focusRequester = focusRequester,
                valueUnit = "reps",
                defaultText = defaultText,
                userInput = userInput,
                onInputChanged = { userInput = it },
                modifier = Modifier
            )
        }

        RecordType.TIME -> {
            AddTimeRecordScreen(
                onAdjustButtonClicked = {},
                modifier = Modifier
            )
        }

        RecordType.DISTANCE -> {
            val defaultText = "0.0"
            var userInput by remember { mutableStateOf(defaultText) }
            AddDistanceRecordScreen(
                focusRequester = focusRequester,
                valueUnit = "km",
                defaultText = defaultText,
                userInput = userInput,
                onInputChanged = { userInput = it },
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun FinalSubmitActions(
    onOkButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    AddRecordActionsScreen(
        onOKButtonClicked = onOkButtonClicked,
        onCancelButtonClicked = onCancelButtonClicked,
        modifier = Modifier
    )
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
        AddRecordScreen(
            persistentSetOf(),
            focusRequester = FocusRequester(),
            onRequestFocus = {},
            onCancelButtonClicked = {}
        )
    }
}
