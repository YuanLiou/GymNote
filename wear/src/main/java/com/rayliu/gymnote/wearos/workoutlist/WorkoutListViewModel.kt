package com.rayliu.gymnote.wearos.workoutlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayliu.commonmain.domain.usecase.GetWorkoutInfos
import com.rayliu.gymnote.wearos.navigation.CATEGORY_ID_NAV_ARGUMENT
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WorkoutListViewModel(
    savedStateHandle: SavedStateHandle,
    getWorkoutInfo: GetWorkoutInfos
) : ViewModel() {
    private val categoryId: Int = savedStateHandle[CATEGORY_ID_NAV_ARGUMENT] ?: -1

    val workoutInfoState =
        getWorkoutInfo(categoryId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = persistentListOf()
        )
}