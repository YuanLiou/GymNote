package com.rayliu.gymnote.wearos.addrecord.recordinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CompactChip
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.R
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants

@Composable
fun AddTimeRecordScreen(
    onAdjustButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    defaultText: String = "00:00:00"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                defaultText,
                fontSize = 22.sp
            )
            CompactChip(
                onClick = onAdjustButtonClicked,
                label = {
                    Text(stringResource(id = R.string.button_action_adjust))
                },
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(
    group = "Screen Preview",
    widthDp = PreviewConstants.WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL
)
@Composable
private fun AddTimeRecordPreview() {
    GymNoteTheme {
        AddTimeRecordScreen(
            onAdjustButtonClicked = {}
        )
    }
}
