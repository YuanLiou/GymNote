package com.rayliu.gymnote.wearos.categorylist

import androidx.lifecycle.ViewModel
import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.commonmain.domain.usecase.GetCategoryUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CategoryListViewModel(
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {

    fun provideCategories(): Flow<ImmutableList<SportCategory>> {
        return getCategoryUseCase()
    }
}