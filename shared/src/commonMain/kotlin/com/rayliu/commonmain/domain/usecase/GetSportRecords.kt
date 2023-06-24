package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.Record
import com.rayliu.commonmain.domain.repository.SportRecordRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetSportRecords(
    private val sportRecordRepository: SportRecordRepository
) {
    operator fun invoke(workoutInfoId: Int): Flow<ImmutableList<Record>> {
        return sportRecordRepository.getSportRecords(workoutInfoId).map {
            it.toImmutableList()
        }
    }
}