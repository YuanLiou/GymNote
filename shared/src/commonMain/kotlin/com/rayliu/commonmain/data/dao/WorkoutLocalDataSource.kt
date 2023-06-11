package com.rayliu.commonmain.data.dao

import com.rayliu.commonmain.data.database.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutLocalDataSource {
    suspend fun getWorkoutsByCategoryId(id: Long): List<Workout>
    suspend fun updateInitialDate(initialDate: String)
    suspend fun getInitialGeneratedWorkoutCounts(): Int
    fun getWorkoutsFlow(categoryId: Long): Flow<List<Workout>>
}