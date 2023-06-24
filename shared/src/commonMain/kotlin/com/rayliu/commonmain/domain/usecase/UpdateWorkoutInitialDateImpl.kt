package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.data.database.DateTimeConverter
import com.rayliu.commonmain.domain.repository.WorkoutInfoRepository
import org.koin.core.annotation.Factory

@Factory
class UpdateWorkoutInitialDateImpl(
    private val dateTimeConverter: DateTimeConverter,
    private val repository: WorkoutInfoRepository
) : UpdateWorkoutInitialDate {
    override suspend operator fun invoke() {
        repository.updateInitialDateIfNeeded(dateTimeConverter.provideNowTimeStamps())
    }
}
