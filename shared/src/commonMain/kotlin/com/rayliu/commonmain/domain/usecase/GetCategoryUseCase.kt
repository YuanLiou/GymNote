package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.Category
import com.rayliu.commonmain.domain.repository.CategoryRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.core.annotation.Factory

@Factory
class GetCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): ImmutableList<Category> {
        return categoryRepository.provideBasicCategories().toImmutableList()
    }
}