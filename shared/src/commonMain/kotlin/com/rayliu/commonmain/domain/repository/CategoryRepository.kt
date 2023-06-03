package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.domain.model.SportCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun provideBasicCategories(): Flow<List<SportCategory>>
}