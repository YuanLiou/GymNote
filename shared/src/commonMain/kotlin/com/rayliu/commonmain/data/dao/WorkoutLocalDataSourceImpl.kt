package com.rayliu.commonmain.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.rayliu.commonmain.data.database.AppDatabase
import com.rayliu.commonmain.data.database.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
class WorkoutLocalDataSourceImpl(
    @Named("IO") private val ioDispatcher: CoroutineDispatcher,
    @Named("Default") private val defaultDispatcher: CoroutineDispatcher,
    appDatabase: AppDatabase
) : WorkoutLocalDataSource {

    private val queries = appDatabase.workoutQueries

    override suspend fun getWorkoutsByCategoryId(id: Long): List<Workout> =
        withContext(ioDispatcher) {
            queries.getWorkoutsByCategoryId(id).executeAsList()
        }

    override suspend fun getWorkout(id: Long): Workout? {
        return queries.getWorkoutById(id).executeAsOneOrNull()
    }

    override suspend fun updateInitialDate(initialDate: String) = withContext(ioDispatcher) {
        queries.updateInitalCreateDate(initialDate, initialDate)
    }

    override suspend fun getInitialGeneratedWorkoutCounts(): Int {
        return queries.getInitialGenerateWorkoutCounts().executeAsOneOrNull()?.toInt() ?: 0
    }

    override fun getWorkoutsFlow(categoryId: Long): Flow<List<Workout>> {
        return queries.getWorkoutsByCategoryId(categoryId).asFlow().mapToList(defaultDispatcher)
    }
}