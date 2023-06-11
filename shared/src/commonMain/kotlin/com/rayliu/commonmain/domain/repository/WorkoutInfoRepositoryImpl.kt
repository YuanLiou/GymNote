package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.data.dao.WorkoutLocalDataSource
import org.koin.core.annotation.Factory

@Factory
class WorkoutInfoRepositoryImpl(
    private val localDataSource: WorkoutLocalDataSource
) : WorkoutInfoRepository {
    override suspend fun updateInitialDateIfNeeded(initialDate: String) {
        if (localDataSource.getInitialGeneratedWorkoutCounts() > 0) {
            localDataSource.updateInitialDate(initialDate)
        }
    }
}