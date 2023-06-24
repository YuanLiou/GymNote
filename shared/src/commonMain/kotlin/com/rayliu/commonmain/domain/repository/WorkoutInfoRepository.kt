package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.domain.model.WorkoutInfo
import kotlinx.coroutines.flow.Flow

interface WorkoutInfoRepository {
    suspend fun updateInitialDateIfNeeded(initialDate: String)
    suspend fun getWorkoutInfo(id: Int): WorkoutInfo?
    fun provideWorkoutInfos(categoryId: Int): Flow<List<WorkoutInfo>>
}