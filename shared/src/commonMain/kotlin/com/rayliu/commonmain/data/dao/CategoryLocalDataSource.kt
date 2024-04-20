package com.rayliu.commonmain.data.dao

import com.rayliu.commonmain.data.database.Category
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {
    suspend fun getCategoryById(id: Long): Category?

    fun getCategories(): Flow<List<Category>>
}