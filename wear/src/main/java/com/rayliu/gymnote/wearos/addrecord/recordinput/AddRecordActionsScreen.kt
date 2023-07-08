package com.rayliu.gymnote.wearos.addrecord.recordinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants

@Composable
fun AddRecordActionsScreen(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Add Record Actions Screen")
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
        AddRecordActionsScreen()
    }
}
