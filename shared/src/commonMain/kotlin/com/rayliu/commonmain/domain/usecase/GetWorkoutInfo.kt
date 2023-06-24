package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.WorkoutInfo
import com.rayliu.commonmain.domain.repository.WorkoutInfoRepository
import org.koin.core.annotation.Factory

@Factory
class GetWorkoutInfo(
    private val workoutInfoRepository: WorkoutInfoRepository
) {

    suspend operator fun invoke(id: Int): WorkoutInfo? {
        return workoutInfoRepository.getWorkoutInfo(id)
    }
}