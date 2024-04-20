package com.rayliu.gymnote.wearos.addrecord.recordinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import com.rayliu.gymnote.wearos.ui.OptionItem

@Composable
fun AddRecordActionsScreen(
    onOKButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            OptionItem(
                title = "OK",
                onItemClick = onOKButtonClicked,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            OptionItem(
                title = "Cancel",
                onItemClick = onCancelButtonClicked,
                modifier = Modifier.padding(vertical = 4.dp)
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
private fun AddRecordActionsScreenPreview() {
    GymNoteTheme {
        AddRecordActionsScreen(
            onOKButtonClicked = {},
            onCancelButtonClicked = {}
        )
    }
}
