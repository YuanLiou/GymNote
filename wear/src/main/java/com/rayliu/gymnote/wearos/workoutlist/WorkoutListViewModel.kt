package com.rayliu.gymnote.wearos.workoutlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.commonmain.domain.usecase.GetWorkoutInfos
import com.rayliu.gymnote.wearos.navigation.CATEGORY_ID_NAV_ARGUMENT
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WorkoutListViewModel(
    savedStateHandle: SavedStateHandle,
    private val getWorkoutInfo: GetWorkoutInfos
) : ViewModel() {

    private val categoryId: Int? = savedStateHandle[CATEGORY_ID_NAV_ARGUMENT]

    fun provideWorkoutInfos(): Flow<ImmutableList<WorkoutInfo>> {
        if (categoryId == null) {
            return emptyFlow()
        }
        return getWorkoutInfo(categoryId)
    }
}