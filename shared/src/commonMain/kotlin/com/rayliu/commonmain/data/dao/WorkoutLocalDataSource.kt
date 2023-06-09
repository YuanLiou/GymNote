package com.rayliu.commonmain.data.dao

import com.rayliu.commonmain.data.database.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutLocalDataSource {
    suspend fun getWorkoutByCategoryId(id: Long): List<Workout>
    suspend fun updateInitialDate(initialDate: String)
    suspend fun checkIsInitialDateInserted(): Boolean
    fun getWorkoutsFlow(categoryId: Long): Flow<List<Workout>>
}