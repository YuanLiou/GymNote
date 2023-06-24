package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.domain.model.Record
import kotlinx.coroutines.flow.Flow

interface SportRecordRepository {
    fun getSportRecords(workoutInfoId: Int): Flow<List<Record>>
    suspend fun insertNewRecord(record: Record)
    suspend fun updateRecord(record: Record)
    suspend fun deleteRecord(record: Record)
}