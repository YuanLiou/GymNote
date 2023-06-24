package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.data.dao.WorkoutRecordDataSource
import com.rayliu.commonmain.data.mapper.RecordDetailsMapper
import com.rayliu.commonmain.data.mapper.RecordListMapper
import com.rayliu.commonmain.domain.model.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class SportRecordRepositoryImpl(
    private val workoutRecordDataSource: WorkoutRecordDataSource,
    private val recordDetailsMapper: RecordDetailsMapper,
    private val recordListMapper: RecordListMapper
) : SportRecordRepository {
    override fun getSportRecords(workoutInfoId: Int): Flow<List<Record>> {
        return workoutRecordDataSource.getWorkoutRecordDetails(workoutInfoId.toLong()).map {
            recordListMapper.map(it)
        }
    }

    override suspend fun insertNewRecord(record: Record) {
        workoutRecordDataSource.insertRecordDetails(recordDetailsMapper.map(record))
    }

    override suspend fun updateRecord(record: Record) {
        workoutRecordDataSource.updateRecordDetails(recordDetailsMapper.map(record))
    }

    override suspend fun deleteRecord(record: Record) {
        workoutRecordDataSource.deleteRecordDetails(record.id.toLong())
    }
}