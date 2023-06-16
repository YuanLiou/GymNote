package com.rayliu.gymnote.wearos.workoutlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.wearos.theme.GymNoteTheme

@Composable
fun WorkoutListScreen(
    categoryId: Int,
    listState: ScalingLazyListState,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Hello id: $categoryId")
    }
}

@Preview
@Composable
private fun WorkoutListScreenPreview() {
    GymNoteTheme {
        WorkoutListScreen(
            13,
            ScalingLazyListState()
        )
    }
}