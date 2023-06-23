package com.rayliu.commonmain.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.rayliu.commonmain.data.database.AppDatabase
import com.rayliu.commonmain.data.database.RecordDetails
import com.rayliu.commonmain.data.database.WorkoutRecord
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
class WorkoutRecordDataSourceImpl(
    @Named("IO") private val ioDispatcher: CoroutineDispatcher,
    @Named("Default") private val defaultDispatcher: CoroutineDispatcher,
    appDatabase: AppDatabase
) : WorkoutRecordDataSource {

    private val recordQueries = appDatabase.workoutRecordQueries
    private val detailsQueries = appDatabase.recordDetailsQueries

    override fun getWorkoutRecords(id: Long): Flow<List<WorkoutRecord>> {
        return recordQueries.getWorkoutRecords(id).asFlow().mapToList(defaultDispatcher)
    }

    override fun getWorkoutRecordDetails(workoutRecordId: Long): Flow<List<RecordDetails>> {
        return detailsQueries.getRecordDetails(workoutRecordId).asFlow().mapToList(defaultDispatcher)
    }

    override suspend fun insertRecordDetails(
        workoutRecordId: Long,
        createAt: String,
        sportRecordTypeId: Long,
        weight: Double?,
        reps: Long?,
        time: String?,
        distance: Double?
    ) = withContext(ioDispatcher) {
        detailsQueries.insertNewRecord(
            id = null,
            workoutRecordId = workoutRecordId,
            createAt = createAt,
            lastModified = createAt,
            sportRecordTypeId = sportRecordTypeId,
            weight = weight,
            reps = reps,
            time = time,
            distance = distance
        )
    }

    override suspend fun updateRecordDetails(
        id: Long,
        lastModified: String,
        weight: Double?,
        reps: Long?,
        time: String?,
        distance: Double?
    ) = withContext(ioDispatcher) {
        detailsQueries.updateRecord(
            id = id,
            lastModified = lastModified,
            weight = weight,
            reps = reps,
            time = time,
            distance = distance
        )
    }

    override suspend fun deleteRecordDetails(id: Long) = withContext(ioDispatcher) {
        detailsQueries.deleteRecord(id)
    }
}