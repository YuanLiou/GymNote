package com.rayliu.commonmain.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.rayliu.commonmain.data.database.AppDatabase
import com.rayliu.commonmain.data.database.RecordDetails
import com.rayliu.commonmain.domain.model.RECORD_EMPTY_ID
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

    private val detailsQueries = appDatabase.recordDetailsQueries

    override fun getWorkoutRecordDetails(workoutId: Long): Flow<List<RecordDetails>> {
        return detailsQueries.getRecordDetails(workoutId).asFlow().mapToList(defaultDispatcher)
    }

    override suspend fun insertRecordDetails(
        recordDetails: RecordDetails
    ) = withContext(ioDispatcher) {
        detailsQueries.insertNewRecord(
            id = null,
            workoutId = recordDetails.workoutId,
            createAt = recordDetails.createAt,
            lastModified = recordDetails.createAt,
            sportRecordTypeId = recordDetails.sportRecordTypeId,
            weight = recordDetails.weight,
            reps = recordDetails.reps,
            time = recordDetails.time,
            distance = recordDetails.distance
        )
    }

    override suspend fun updateRecordDetails(
        recordDetails: RecordDetails
    ) = withContext(ioDispatcher) {
        if (recordDetails.id == RECORD_EMPTY_ID.toLong()) {
            return@withContext
        }

        detailsQueries.updateRecord(
            id = recordDetails.id,
            lastModified = recordDetails.lastModified,
            weight = recordDetails.weight,
            reps = recordDetails.reps,
            time = recordDetails.time,
            distance = recordDetails.distance
        )
    }

    override suspend fun deleteRecordDetails(id: Long) = withContext(ioDispatcher) {
        detailsQueries.deleteRecord(id)
    }
}