package com.rayliu.gymnote.wearos.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.wearos.theme.PreviewConstants

@Composable
fun OptionItem(
    categoryName: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Chip(
        modifier = modifier.fillMaxWidth(),
        onClick = onItemClick,
        label = {
            Text(categoryName)
        }
    )
}

@Preview(
    group = "User Select Options",
    widthDp = PreviewConstants.WEAR_PREVIEW_ROW_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_ROW_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL,
    uiMode = PreviewConstants.WEAR_PREVIEW_UI_MODE,
    backgroundColor = PreviewConstants.WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = PreviewConstants.WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
private fun OptionItemPreview() {
    OptionItem(
        categoryName = "Testing Chip",
        onItemClick = {}
    )
}
