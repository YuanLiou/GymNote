package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.repository.WorkoutInfoRepository
import kotlinx.datetime.Instant
import org.koin.core.annotation.Factory

@Factory
class UpdateWorkoutInitialDate(
    private val repository: WorkoutInfoRepository
) {
    suspend operator fun invoke() {
        val timeStamps = Instant.toString()
        println("timeStamps: $timeStamps")
//        repository.updateInitialDateIfNeeded(Instant.toString())
    }
}