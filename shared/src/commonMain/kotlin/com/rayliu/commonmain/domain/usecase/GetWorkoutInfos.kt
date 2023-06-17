package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.commonmain.domain.repository.WorkoutInfoRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetWorkoutInfos(
    private val workoutInfoRepository: WorkoutInfoRepository
) {
    operator fun invoke(sportCategory: SportCategory): Flow<ImmutableList<WorkoutInfo>> {
        return workoutInfoRepository.provideWorkoutInfos(sportCategory.id).map {
            it.toImmutableList()
        }
    }
}