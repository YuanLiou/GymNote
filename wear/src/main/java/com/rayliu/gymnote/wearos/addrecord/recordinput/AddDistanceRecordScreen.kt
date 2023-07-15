package com.rayliu.gymnote.wearos.addrecord.recordinput

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.rayliu.gymnote.R
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import com.rayliu.gymnote.wearos.ui.ValueInput
import com.rayliu.gymnote.wearos.utils.InputUtils

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

        val context = LocalContext.current
        val waringText = stringResource(id = R.string.warning_input_type_is_not_number)

        ValueInput(
            currentInput = userInput,
            focusRequester = focusRequester,
            isMinusValueEnabled = userInput.toInt() > minimumValue,
            onUserInputText = { result ->
                val newInput = result?.toString()?.trim() ?: defaultText
                if (InputUtils.isNumeric(newInput) && newInput.toInt() >= minimumValue) {
                    userInput = newInput
                } else {
                    Toast.makeText(context, waringText, Toast.LENGTH_SHORT).show()
                }
            },
            onUserClickPlusButton = {
                userInput = (it.toInt() + step).toString()
            },
            onUserClickMinorButton = {
                val value = it.toInt()
                if (value > minimumValue) {
                    userInput = (value - step).toString()
                }
            }
        )
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
