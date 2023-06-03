package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.commonmain.domain.repository.CategoryRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<ImmutableList<SportCategory>> {
        return categoryRepository.provideBasicCategories().map {
            it.toImmutableList()
        }
    }
}