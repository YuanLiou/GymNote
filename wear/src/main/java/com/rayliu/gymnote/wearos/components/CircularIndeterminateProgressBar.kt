package com.rayliu.gymnote.wearos.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean,
    modifier: Modifier = Modifier
) {
    if (isDisplayed) {
        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                indicatorColor = MaterialTheme.colors.secondary,
                trackColor = MaterialTheme.colors.onBackground.copy(alpha = 0.1f),
                strokeWidth = 4.dp,
                modifier = modifier.size(48.dp)
            )
        }
    }
}

@Preview
@Composable
fun CircularIndeterminateProgressBarPreview() {
    CircularIndeterminateProgressBar(isDisplayed = true)
}