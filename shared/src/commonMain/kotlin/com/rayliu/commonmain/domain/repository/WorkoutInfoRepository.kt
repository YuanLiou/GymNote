package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.domain.model.WorkoutInfo
import kotlinx.coroutines.flow.Flow

interface WorkoutInfoRepository {
    suspend fun updateInitialDateIfNeeded(initialDate: String)
    fun provideWorkoutInfos(categoryId: Int): Flow<List<WorkoutInfo>>
}