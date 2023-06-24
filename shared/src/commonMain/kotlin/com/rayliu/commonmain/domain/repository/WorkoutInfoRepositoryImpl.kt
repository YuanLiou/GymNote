package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.data.dao.WorkoutLocalDataSource
import com.rayliu.commonmain.data.mapper.WorkoutInfoListMapper
import com.rayliu.commonmain.data.mapper.WorkoutInfoMapper
import com.rayliu.commonmain.domain.model.WorkoutInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class WorkoutInfoRepositoryImpl(
    private val localDataSource: WorkoutLocalDataSource,
    private val workoutInfoListMapper: WorkoutInfoListMapper,
    private val workoutInfoMapper: WorkoutInfoMapper
) : WorkoutInfoRepository {
    override suspend fun updateInitialDateIfNeeded(initialDate: String) {
        if (localDataSource.getInitialGeneratedWorkoutCounts() > 0) {
            localDataSource.updateInitialDate(initialDate)
        }
    }

    override suspend fun getWorkoutInfo(id: Int): WorkoutInfo? {
        return localDataSource.getWorkout(id.toLong())?.let {
            workoutInfoMapper.map(it)
        }
    }

    override fun provideWorkoutInfos(categoryId: Int): Flow<List<WorkoutInfo>> {
        return localDataSource.getWorkoutsFlow(categoryId.toLong()).map {
            workoutInfoListMapper.map(it)
        }
    }
}