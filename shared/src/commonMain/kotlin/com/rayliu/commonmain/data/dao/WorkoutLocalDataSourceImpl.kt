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

    override suspend fun getWorkoutByCategoryId(id: Long): List<Workout> =
        withContext(ioDispatcher) {
            queries.getWorkoutByCategoryId(id).executeAsList()
        }

    override suspend fun updateInitialDate(initialDate: String) = withContext(ioDispatcher) {
        queries.updateInitalCreateDate(initialDate, initialDate)
    }

    override suspend fun checkIsInitialDateInserted(): Boolean = withContext(ioDispatcher) {
        queries.isInitalCreated().executeAsOneOrNull()?.let { it > 0 } ?: false
    }

    override fun getWorkoutsFlow(categoryId: Long): Flow<List<Workout>> {
        return queries.getWorkoutByCategoryId(categoryId).asFlow().mapToList(defaultDispatcher)
    }
}