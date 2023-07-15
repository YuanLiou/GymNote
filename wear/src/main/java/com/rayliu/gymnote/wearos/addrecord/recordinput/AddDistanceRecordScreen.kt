package com.rayliu.gymnote.wearos.addrecord.recordinput

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CompactButton
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.onRotaryInputAccumulatedWithFocus
import com.rayliu.gymnote.R
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import com.rayliu.gymnote.wearos.ui.WearEditText
import com.rayliu.gymnote.wearos.utils.InputUtils
import kotlin.math.roundToInt

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun AddDistanceRecordScreen(
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        val step = 1
        val minimumValue = 0
        val defaultText = "0"
        var userInput by remember { mutableStateOf(defaultText) }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .onRotaryInputAccumulatedWithFocus(
                    focusRequester = focusRequester,
                    isLowRes = true // bazel device is low res
                ) {
                    val accumulatedValue = it.roundToInt()
                    if (accumulatedValue > 0) {
                        userInput = (userInput.toInt() + step).toString()
                    } else if (accumulatedValue < 0 && userInput.toInt() > minimumValue) {
                        userInput = (userInput.toInt() - step).toString()
                    }
                }
        ) {
            val context = LocalContext.current
            val waringText = stringResource(id = R.string.warning_input_type_is_not_number)

            CompactButton(
                onClick = {
                    userInput = (userInput.toInt() + step).toString()
                },
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Image(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "add value"
                )
            }

            WearEditText(
                text = userInput,
                onUserInputText = { result ->
                    val newInput = result?.toString()?.trim() ?: "0"
                    if (InputUtils.isNumeric(newInput) && newInput.toInt() >= minimumValue) {
                        userInput = newInput
                    } else {
                        Toast.makeText(context, waringText, Toast.LENGTH_SHORT).show()
                    }
                },
                fontSize = 20.sp
            )

            CompactButton(
                onClick = {
                    userInput = (userInput.toInt() - step).toString()
                },
                enabled = userInput.toInt() > minimumValue,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Image(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = "remove value"
                )
            }
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
private fun AddDistanceRecordPreview() {
    GymNoteTheme {
        AddDistanceRecordScreen(
            FocusRequester()
        )
    }
}
