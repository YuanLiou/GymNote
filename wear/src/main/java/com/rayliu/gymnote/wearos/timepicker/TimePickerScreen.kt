package com.rayliu.gymnote.wearos.timepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.horologist.composables.TimePicker
import com.rayliu.gymnote.wearos.addrecord.recordinput.DEFAULT_TIME
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toLocalTime

@Composable
fun TimePickerScreen(
    focusRequester: FocusRequester,
    onTimeConfirm: (String) -> Unit,
    modifier: Modifier = Modifier,
    defaultTime: LocalTime? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        val localTime =
            defaultTime?.toJavaLocalTime() ?: DEFAULT_TIME.toLocalTime().toJavaLocalTime()
        TimePicker(
            time = localTime,
            onTimeConfirm = {
                val hourText = "%02d".format(it.hour)
                val minuteText = "%02d".format(it.minute)
                val secondText = "%02d".format(it.second)
                onTimeConfirm("$hourText:$minuteText:$secondText")
            },
            modifier = Modifier
                .padding(4.dp)
                .focusRequester(focusRequester)
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
fun TimePickerScreenPreview() {
    GymNoteTheme {
        TimePickerScreen(
            focusRequester = FocusRequester(),
            onTimeConfirm = {}
        )
    }
}
