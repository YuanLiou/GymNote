package com.rayliu.commonmain.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.rayliu.commonmain.data.database.AppDatabase
import com.rayliu.commonmain.data.database.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
class CategoryLocalDataSourceImpl(
    @Named("IO") private val ioDispatcher: CoroutineDispatcher,
    @Named("Default") private val defaultDispatcher: CoroutineDispatcher,
    appDatabase: AppDatabase
) : CategoryLocalDataSource {

    private val queries = appDatabase.categoryQueries

    override suspend fun getCategoryById(id: Long): Category? = withContext(ioDispatcher) {
        queries.getCategoryById(id).executeAsOneOrNull()
    }

    override fun getCategories(): Flow<List<Category>> {
        return queries.getAllCategories().asFlow().mapToList(defaultDispatcher)
    }
}