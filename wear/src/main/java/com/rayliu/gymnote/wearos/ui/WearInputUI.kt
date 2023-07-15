package com.rayliu.gymnote.wearos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CompactButton
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.onRotaryInputAccumulatedWithFocus
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import kotlin.math.roundToInt

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun ValueInput(
    focusRequester: FocusRequester,
    onUserInputText: (CharSequence?, minimumValue: Int) -> Unit,
    onUserClickPlusButton: (String) -> Unit,
    onUserClickMinorButton: (String) -> Unit,
    modifier: Modifier = Modifier,
    currentInput: String = "0",
    minimumValue: Int = 0
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .onRotaryInputAccumulatedWithFocus(
                focusRequester = focusRequester,
                isLowRes = true // bazel device is low res
            ) {
                val accumulatedValue = it.roundToInt()
                if (accumulatedValue > 0) {
                    onUserClickPlusButton(currentInput)
                } else if (accumulatedValue < 0 && currentInput.toInt() > minimumValue) {
                    onUserClickMinorButton(currentInput)
                }
            }
    ) {
        CompactButton(
            onClick = { onUserClickPlusButton(currentInput) },
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Image(
                imageVector = Icons.Rounded.Add,
                contentDescription = "add value"
            )
        }

        WearEditText(
            text = currentInput,
            onUserInputText = {
                onUserInputText(it, minimumValue)
            },
            fontSize = 20.sp
        )

        CompactButton(
            onClick = { onUserClickMinorButton(currentInput) },
            enabled = currentInput.toInt() > minimumValue,
            modifier = Modifier.padding(top = 6.dp)
        ) {
            Image(
                imageVector = Icons.Rounded.Remove,
                contentDescription = "remove value"
            )
        }
    }
}

@Preview(
    group = "Component Preview",
    widthDp = PreviewConstants.WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL
)
@Composable
fun ValueInputPreview() {
    GymNoteTheme {
        ValueInput(
            focusRequester = FocusRequester(),
            onUserInputText = { _, _ -> },
            onUserClickPlusButton = {},
            onUserClickMinorButton = {},
        )
    }
}