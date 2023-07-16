package com.rayliu.gymnote.wearos.addrecord

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayliu.commonmain.domain.model.SportRecordType
import com.rayliu.commonmain.domain.usecase.GetWorkoutInfo
import com.rayliu.gymnote.wearos.navigation.WORKOUT_ID_NAV_ARGUMENT
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddRecordViewModel(
    savedStateHandle: SavedStateHandle,
    private val getWorkoutInfo: GetWorkoutInfo
) : ViewModel() {

    private val workoutId: Int = savedStateHandle[WORKOUT_ID_NAV_ARGUMENT] ?: -1

    val recordInputTypes = mutableStateOf<ImmutableSet<RecordType>>(persistentSetOf())
    val timeRecord = mutableStateOf<String?>(null)

    fun performPreScreenTasks() {
        viewModelScope.launch {
            val info = getWorkoutInfo(workoutId) ?: return@launch
            when (info.sportRecordType) {
                SportRecordType.WEIGHT_REPS -> {
                    recordInputTypes.value = persistentSetOf(RecordType.WEIGHT, RecordType.REPS)
                }
                SportRecordType.WEIGHT_TIME -> {
                    recordInputTypes.value = persistentSetOf(RecordType.WEIGHT, RecordType.TIME)
                }
                SportRecordType.DISTANCE_TIME -> {
                    recordInputTypes.value = persistentSetOf(RecordType.DISTANCE, RecordType.TIME)
                }
                SportRecordType.WEIGHT -> {
                    recordInputTypes.value = persistentSetOf(RecordType.WEIGHT)
                }
                SportRecordType.REPS -> {
                    recordInputTypes.value = persistentSetOf(RecordType.REPS)
                }
                SportRecordType.TIME -> {
                    recordInputTypes.value = persistentSetOf(RecordType.TIME)
                }
                SportRecordType.DISTANCE -> {
                    recordInputTypes.value = persistentSetOf(RecordType.DISTANCE)
                }
                else -> {}
            }
        }
    }

    fun setTimeRecord(userInputTimeRecord: String?) {
        timeRecord.value = userInputTimeRecord
    }
}