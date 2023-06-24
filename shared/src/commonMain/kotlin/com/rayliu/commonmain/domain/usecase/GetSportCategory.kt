package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.commonmain.domain.repository.CategoryRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetSportCategory(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<ImmutableList<SportCategory>> {
        return categoryRepository.provideBasicCategories()
    }
}