package com.rayliu.gymnote.wearos.addrecord

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.commonmain.domain.usecase.GetWorkoutInfo
import com.rayliu.gymnote.wearos.navigation.WORKOUT_ID_NAV_ARGUMENT
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddRecordViewModel(
    savedStateHandle: SavedStateHandle,
    private val getWorkoutInfo: GetWorkoutInfo
) : ViewModel() {

    private val workoutId: Int = savedStateHandle[WORKOUT_ID_NAV_ARGUMENT] ?: -1

    val workoutInfo = mutableStateOf<WorkoutInfo?>(null)

    fun performPreScreenTasks() {
        viewModelScope.launch {
            workoutInfo.value = getWorkoutInfo(workoutId)
        }
    }
}