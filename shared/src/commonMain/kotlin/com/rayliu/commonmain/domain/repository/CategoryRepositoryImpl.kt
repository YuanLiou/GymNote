package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.data.dao.CategoryLocalDataSource
import com.rayliu.commonmain.data.mapper.CategoryListMapper
import com.rayliu.commonmain.domain.model.SportCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class CategoryRepositoryImpl(
    private val localDataSource: CategoryLocalDataSource,
    private val categoryListMapper: CategoryListMapper
) : CategoryRepository {
    override fun provideBasicCategories(): Flow<List<SportCategory>> {
        return localDataSource.getCategories().map {
            categoryListMapper.map(it)
        }
    }
}