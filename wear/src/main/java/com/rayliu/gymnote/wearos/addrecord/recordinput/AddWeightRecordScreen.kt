package com.rayliu.gymnote.wearos.addrecord.recordinput

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.CompactButton
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.R
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import com.rayliu.gymnote.wearos.ui.ValueInput
import com.rayliu.gymnote.wearos.utils.InputUtils

@Composable
fun AddWeightRecordScreen(
    focusRequester: FocusRequester,
    valueUnit: String,
    defaultText: String,
    userInput: String,
    onInputChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        val step = 0.5f
        val minimumValue = 0.0f

        val context = LocalContext.current
        val waringText = stringResource(id = R.string.warning_input_type_is_not_number)

        val undoButtonVisibility =
            if (userInput != defaultText) {
                1f
            } else {
                0f
            }

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            CompactButton(
                onClick = { onInputChanged(defaultText) },
                modifier = Modifier.alpha(undoButtonVisibility)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Undo,
                    contentDescription = "undo"
                )
            }
        }

        ValueInput(
            currentInput = userInput,
            focusRequester = focusRequester,
            isMinusValueEnabled = userInput.toFloat() > minimumValue,
            onUserInputText = { result ->
                val newInput = result?.toString()?.trim() ?: defaultText
                if (InputUtils.isNumeric(newInput) && newInput.toFloat() >= minimumValue) {
                    onInputChanged(newInput)
                } else {
                    Toast.makeText(context, waringText, Toast.LENGTH_SHORT).show()
                }
            },
            onUserClickPlusButton = {
                val newInput = (it.toFloat() + step).toString()
                onInputChanged(newInput)
            },
            onUserClickMinorButton = {
                val value = it.toFloat()
                if (value > minimumValue) {
                    val newInput = (value - step).toString()
                    onInputChanged(newInput)
                }
            }
        )

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                // For layout only
                CompactButton(
                    modifier = Modifier.alpha(0f),
                    enabled = false,
                    onClick = {}
                ) {}

                Text(
                    valueUnit,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
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
private fun AddWeightRecordPreview() {
    GymNoteTheme {
        AddWeightRecordScreen(
            focusRequester = FocusRequester(),
            valueUnit = "kg",
            defaultText = "0.0",
            userInput = "0.0",
            onInputChanged = {}
        )
    }
}
