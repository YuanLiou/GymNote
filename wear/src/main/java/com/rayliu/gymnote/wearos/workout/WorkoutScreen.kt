package com.rayliu.gymnote.wearos.workout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.Text
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll
import com.rayliu.commonmain.domain.model.DistanceTimeRecord
import com.rayliu.commonmain.domain.model.Record
import com.rayliu.commonmain.domain.model.SportRecordType
import com.rayliu.commonmain.domain.model.WeightRepsRecord
import com.rayliu.commonmain.domain.model.WeightTimeRecord
import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.gymnote.R
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import com.rayliu.gymnote.wearos.ui.WearButton
import com.rayliu.gymnote.wearos.ui.WearCard
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun WorkoutScreen(
    listState: ScalingLazyListState,
    focusRequester: FocusRequester,
    workoutInfo: WorkoutInfo?,
    records: ImmutableList<Record>,
    onAddButtonClicked: () -> Unit,
    onCardClicked: (Record) -> Unit,
    modifier: Modifier = Modifier
) {
    ScalingLazyColumn(
        modifier = modifier
            .rotaryWithScroll(focusRequester, listState)
            .fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        state = listState,
    ) {
        item {
            WearButton(
                onClick = onAddButtonClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
        item {
            Text(
                workoutInfo?.name.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(bottom = 12.dp),
            )
        }

        records.forEach {
            item {
                ShowRecordCards(it, onCardClicked)
            }
        }
    }
}

@Composable
private fun ShowRecordCards(
    record: Record,
    onCardClicked: (Record) -> Unit
) = when (record) {
    is WeightRepsRecord -> {
        WearCard(
            cardTitle = stringResource(id = R.string.workout_record_kg, record.weight.toString()),
            cardAppName = record.lastModified.toString(),
            message = stringResource(id = R.string.workout_record_reps, record.reps.toString()),
            onCardClicked = {
                onCardClicked(record)
            }
        )
    }
    is DistanceTimeRecord -> {
        WearCard(
            cardTitle = stringResource(id = R.string.workout_record_km, record.distance.toString()),
            cardAppName = record.lastModified.toString(),
            message = record.time,
            onCardClicked = {
                onCardClicked(record)
            }
        )
    }
    is WeightTimeRecord -> {
        WearCard(
            cardTitle = stringResource(id = R.string.workout_record_kg, record.weight.toString()),
            cardAppName = record.lastModified.toString(),
            message = record.time,
            time = "",
            onCardClicked = {
                onCardClicked(record)
            }
        )
    }
    else -> {
        // no-cards-display
    }
}

@Preview(
    group = "Screen Preview",
    widthDp = PreviewConstants.WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL
)
@Composable
private fun WorkoutListScreenPreview() {
    GymNoteTheme {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        WorkoutScreen(
            listState = ScalingLazyListState(),
            focusRequester = FocusRequester(),
            workoutInfo = WorkoutInfo(
                id = 5108,
                name = "Shannon Dominguez",
                categoryId = 5955,
                sportRecordType = SportRecordType.UNKNOWN,
                createdAt = now,
                lastModified = now
            ),
            records = listOf<Record>().toImmutableList(),
            onAddButtonClicked = {},
            onCardClicked = {},
            modifier = Modifier
        )
    }
}
