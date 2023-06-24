package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.Record
import com.rayliu.commonmain.domain.repository.SportRecordRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.core.annotation.Factory

@Factory
class GetSportRecordsImpl(
    private val sportRecordRepository: SportRecordRepository
) : GetSportRecords {
    override fun invoke(workoutId: Int): Flow<ImmutableList<Record>> {
        if (workoutId == -1) {
            return emptyFlow()
        }
        return sportRecordRepository.getSportRecords(workoutId)
    }
}
