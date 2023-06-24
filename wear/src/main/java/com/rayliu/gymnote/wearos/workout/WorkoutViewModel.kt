package com.rayliu.gymnote.wearos.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rayliu.gymnote.wearos.navigation.WORKOUT_ID_NAV_ARGUMENT
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WorkoutViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val workoutId: Int = savedStateHandle[WORKOUT_ID_NAV_ARGUMENT] ?: -1

    fun provideWorkoutId(): Int {
        return workoutId
    }
}