package com.rayliu.gymnote.wearos.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.SportsScore
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
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll
import com.rayliu.commonmain.domain.model.SportRecordType
import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.gymnote.R
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun WorkoutScreen(
    listState: ScalingLazyListState,
    focusRequester: FocusRequester,
    workoutInfo: WorkoutInfo?,
    onAddButtonClicked: () -> Unit,
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
                    .padding(bottom = 8.dp),
            )
        }
    }
}

@Composable
private fun WearButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.size(ButtonDefaults.LargeButtonSize)
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(id = R.string.add_workout_record),
                modifier = iconModifier
            )
        }
    }
}

@Composable
private fun WearCard(
    cardTitle: String?,
    cardAppName: String?,
    message: String?,
    time: String,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    AppCard(
        title = {
            Text(cardTitle.orEmpty())
        },
        appName = {
            Text(text = cardAppName.orEmpty())
        },
        time = {
            Text(text = time)
        },
        appImage = {
            Icon(
                imageVector = Icons.Rounded.SportsScore,
                contentDescription = stringResource(id = R.string.workout_record_item),
                modifier = iconModifier
            )
        },
        onClick = onCardClicked,
        modifier = modifier
    ) {
        Text(message.orEmpty())
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
            onAddButtonClicked = {},
            modifier = Modifier
        )
    }
}

@Preview(
    group = "Button",
    widthDp = PreviewConstants.WEAR_PREVIEW_ELEMENT_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_ELEMENT_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL,
    uiMode = PreviewConstants.WEAR_PREVIEW_UI_MODE,
    backgroundColor = PreviewConstants.WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = PreviewConstants.WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
private fun WearButtonPreview() {
    GymNoteTheme {
        WearButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)
        )
    }
}

@Preview(
    group = "Card",
    widthDp = 300,
    heightDp = PreviewConstants.WEAR_PREVIEW_ELEMENT_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL,
    uiMode = PreviewConstants.WEAR_PREVIEW_UI_MODE,
    backgroundColor = PreviewConstants.WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = PreviewConstants.WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
private fun WearCardPreview() {
    GymNoteTheme {
        WearCard(
            cardTitle = "cardTitle",
            cardAppName = "cardSubTitle",
            message = "message",
            time = "time",
            onCardClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)
        )
    }
}
