package com.rayliu.commonmain.data.dao

import com.rayliu.commonmain.data.database.RecordDetails
import kotlinx.coroutines.flow.Flow

interface WorkoutRecordDataSource {
    fun getWorkoutRecordDetails(workoutId: Long): Flow<List<RecordDetails>>
    suspend fun insertRecordDetails(recordDetails: RecordDetails)
    suspend fun updateRecordDetails(recordDetails: RecordDetails)
    suspend fun deleteRecordDetails(id: Long)
}