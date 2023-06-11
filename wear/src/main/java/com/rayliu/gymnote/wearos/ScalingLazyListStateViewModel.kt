package com.rayliu.gymnote.wearos

import android.os.Bundle
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.autoSaver
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyListState

// Saves scroll state through process death; inspired by https://issuetracker.google.com/195689777

private const val SCROLL_STATE_KEY = "scrollState"

class ScalingLazyListStateViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val scrollState = savedStateHandle.saveable(
        key = SCROLL_STATE_KEY,
        saver = ScalingLazyListState.Saver
    ) {
        ScalingLazyListState()
    }
}

// Interop between SavedStateHandle and Compose Saver/SaveableStateRegistry
// Can be removed after https://issuetracker.google.com/195689777
private fun <T : Any> SavedStateHandle.saveable(
    key: String,
    saver: Saver<T, out Any> = autoSaver(),
    init: () -> T,
): T {
    @Suppress("UNCHECKED_CAST")
    saver as Saver<T, Any>
    // value is restored using the SavedStateHandle or created via [init] lambda
    val value = get<Bundle?>(key)?.get("value")?.let(saver::restore) ?: init()

    // Hook up saving the state to the SavedStateHandle
    setSavedStateProvider(key) {
        bundleOf("value" to with(saver) { SaverScope { true }.save(value) })
    }
    return value
}
