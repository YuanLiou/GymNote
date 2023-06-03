package com.rayliu.commonmain.data.dao

import com.rayliu.commonmain.data.database.Category
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {
    fun getCategories(): Flow<List<Category>>
}