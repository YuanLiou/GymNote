package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.Record
import com.rayliu.commonmain.domain.repository.SportRecordRepository
import org.koin.core.annotation.Factory

@Factory
class InsertNewSportRecord(
    private val sportRecordRepository: SportRecordRepository
) {
    suspend operator fun invoke(record: Record) {
        return sportRecordRepository.insertNewRecord(record)
    }
}