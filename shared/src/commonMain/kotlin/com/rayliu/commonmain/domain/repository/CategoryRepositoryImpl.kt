package com.rayliu.commonmain.domain.repository

import com.rayliu.commonmain.data.dao.CategoryLocalDataSource
import com.rayliu.commonmain.data.mapper.CategoryListMapper
import com.rayliu.commonmain.domain.model.SportCategory
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class CategoryRepositoryImpl(
    private val localDataSource: CategoryLocalDataSource,
    private val categoryListMapper: CategoryListMapper
) : CategoryRepository {
    override fun provideBasicCategories(): Flow<ImmutableList<SportCategory>> {
        return localDataSource.getCategories().map {
            categoryListMapper.map(it).toImmutableList()
        }
    }
}