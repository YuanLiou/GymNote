package com.rayliu.gymnote.wearos.workout

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.commonmain.domain.usecase.GetSportRecords
import com.rayliu.commonmain.domain.usecase.GetWorkoutInfo
import com.rayliu.gymnote.wearos.navigation.WORKOUT_ID_NAV_ARGUMENT
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WorkoutViewModel(
    savedStateHandle: SavedStateHandle,
    private val getWorkoutInfo: GetWorkoutInfo,
    private val getWorkoutRecords: GetSportRecords
) : ViewModel() {
    private val workoutId: Int = savedStateHandle[WORKOUT_ID_NAV_ARGUMENT] ?: -1

    val workoutRecords = getWorkoutRecords(workoutId)
    val workoutInfo = mutableStateOf<WorkoutInfo?>(null)

    fun performPreScreenTasks() {
        viewModelScope.launch {
            workoutInfo.value = getWorkoutInfo(workoutId)
        }
    }
}