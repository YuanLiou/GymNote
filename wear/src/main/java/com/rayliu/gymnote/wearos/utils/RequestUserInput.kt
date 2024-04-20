package com.rayliu.gymnote.wearos.utils

import android.app.RemoteInput
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender

@Composable
fun launchUserInputIntent(
    text: String,
    onUserInputText: (CharSequence?) -> Unit
): () -> Unit {
    val inputTextKey = "input-text"

    val remoteInputs =
        listOf(
            RemoteInput.Builder(inputTextKey)
                .setLabel(text)
                .wearableExtender {
                    setEmojisAllowed(false)
                    setInputActionType(EditorInfo.IME_ACTION_DONE)
                }
                .build()
        )

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data?.let { intent ->
                val result = RemoteInput.getResultsFromIntent(intent)
                val userInputText = result.getCharSequence(inputTextKey)
                onUserInputText(userInputText)
            }
        }

    val intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)
    return { launcher.launch(intent) }
}