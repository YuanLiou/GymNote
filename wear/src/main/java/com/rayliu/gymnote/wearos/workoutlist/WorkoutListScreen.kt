package com.rayliu.gymnote.wearos.workoutlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.rayliu.commonmain.domain.model.SportsRecordType
import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.ui.OptionItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun WorkoutListScreen(
    workoutInfos: ImmutableList<WorkoutInfo>,
    listState: ScalingLazyListState,
    onWorkoutClicked: (WorkoutInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        WorkoutInfoList(
            workoutInfos = workoutInfos,
            listState = listState,
            onWorkoutClicked = onWorkoutClicked,
            modifier = modifier
        )
    }
}

@Composable
private fun WorkoutInfoList(
    workoutInfos: ImmutableList<WorkoutInfo>,
    listState: ScalingLazyListState,
    onWorkoutClicked: (WorkoutInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentModifier = modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        state = listState
    ) {
        workoutInfos.forEach {
            item {
                OptionItem(
                    categoryName = it.name,
                    onItemClick = {
                        onWorkoutClicked(it)
                    },
                    contentModifier
                )
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutListScreenPreview() {
    GymNoteTheme {
        WorkoutListScreen(
            workoutInfos = persistentListOf(
                WorkoutInfo(
                    id = 6278,
                    name = "Odessa Huber",
                    categoryId = 4839,
                    sportRecordType = SportsRecordType.WEIGHT_REPS,
                    createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                    lastModified = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                ),
                WorkoutInfo(
                    id = 4784,
                    name = "Blanca Willis",
                    categoryId = 7617,
                    sportRecordType = SportsRecordType.WEIGHT_REPS,
                    createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                    lastModified = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                )
            ),
            onWorkoutClicked = {},
            listState = ScalingLazyListState()
        )
    }
}