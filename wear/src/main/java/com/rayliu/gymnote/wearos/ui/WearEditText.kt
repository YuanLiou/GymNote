package com.rayliu.gymnote.wearos.ui

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.wear.compose.material.Text
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.utils.launchUserInputIntent

@Composable
fun WearEditText(
    text: String,
    onUserInputText: (CharSequence?) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    val launchUserInputIntent = launchUserInputIntent(
        text = text,
        onUserInputText = onUserInputText
    )

    Text(
        text = text,
        fontSize = fontSize,
        modifier = modifier
            .clickable {
                launchUserInputIntent()
            }
    )
}

@Preview
@Composable
private fun WearEditTextPreview() {
    GymNoteTheme {
        WearEditText(
            text = "199",
            onUserInputText = { }
        )
    }
}