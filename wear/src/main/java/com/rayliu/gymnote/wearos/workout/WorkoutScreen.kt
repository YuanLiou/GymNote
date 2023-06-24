package com.rayliu.gymnote.wearos.workout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.wearos.theme.GymNoteTheme

@Composable
fun WorkoutScreen(
    listState: ScalingLazyListState,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Text("Hello World")
        }
    }
}

@Preview
@Composable
private fun WorkoutListScreenPreview() {
    GymNoteTheme {
//        WorkoutScreen()
    }
}