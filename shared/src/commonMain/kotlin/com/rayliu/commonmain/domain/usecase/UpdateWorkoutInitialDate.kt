package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.data.database.DateTimeConverter
import com.rayliu.commonmain.domain.repository.WorkoutInfoRepository
import org.koin.core.annotation.Factory

@Factory
class UpdateWorkoutInitialDate(
    private val dateTimeConverter: DateTimeConverter,
    private val repository: WorkoutInfoRepository
) {
    suspend operator fun invoke() {
        repository.updateInitialDateIfNeeded(dateTimeConverter.provideNowTimeStamps())
    }
}