package com.rayliu.commonmain.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.rayliu.commonmain.data.database.AppDatabase
import com.rayliu.commonmain.data.database.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class CategoryLocalDataSourceImpl(appDatabase: AppDatabase) : CategoryLocalDataSource {

    private val queries = appDatabase.categoryQueries

    override fun getCategories(): Flow<List<Category>> {
        return queries.getAllCategories().asFlow().mapToList(Dispatchers.Default)
    }
}