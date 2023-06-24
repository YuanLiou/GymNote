package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.commonmain.domain.repository.WorkoutInfoRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.core.annotation.Factory

@Factory
class GetWorkoutInfosImpl(
    private val workoutInfoRepository: WorkoutInfoRepository
) : GetWorkoutInfos {
    override operator fun invoke(categoryId: Int): Flow<ImmutableList<WorkoutInfo>> {
        if (categoryId < 0) {
            return emptyFlow()
        }
        return workoutInfoRepository.provideWorkoutInfos(categoryId)
    }
}
