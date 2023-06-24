package com.rayliu.gymnote.wearos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.R
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants

@Composable
fun OptionItem(
    categoryName: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Chip(modifier = modifier.fillMaxWidth(), onClick = onItemClick, label = {
        Text(categoryName)
    })
}

@Composable
fun WearButton(
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
fun WearCard(
    cardTitle: String?,
    cardAppName: String?,
    message: String?,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    time: String = ""
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
    group = "Chip",
    widthDp = PreviewConstants.WEAR_PREVIEW_ROW_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_ROW_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL,
    uiMode = PreviewConstants.WEAR_PREVIEW_UI_MODE,
    backgroundColor = PreviewConstants.WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = PreviewConstants.WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
private fun OptionItemPreview() {
    OptionItem(categoryName = "Testing Chip", onItemClick = {})
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
            cardAppName = "cardAppName",
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
