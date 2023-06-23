package com.rayliu.commonmain.data.dao

import com.rayliu.commonmain.data.database.RecordDetails
import com.rayliu.commonmain.data.database.WorkoutRecord
import kotlinx.coroutines.flow.Flow

interface WorkoutRecordDataSource {
    fun getWorkoutRecords(id: Long): Flow<List<WorkoutRecord>>
    fun getWorkoutRecordDetails(workoutRecordId: Long): Flow<List<RecordDetails>>
    suspend fun insertRecordDetails(
        workoutRecordId: Long,
        createAt: String,
        weight: Double?,
        reps: Long?,
        time: String?,
        distance: Double?
    )
    suspend fun updateRecordDetails(
        id: Long,
        lastModified: String,
        weight: Double?,
        reps: Long?,
        time: String?,
        distance: Double?
    )
    suspend fun deleteRecordDetails(id: Long)
}