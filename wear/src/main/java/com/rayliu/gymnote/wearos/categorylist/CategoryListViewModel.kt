package com.rayliu.gymnote.wearos.categorylist

import androidx.lifecycle.ViewModel
import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.commonmain.domain.usecase.GetSportCategory
import com.rayliu.commonmain.domain.usecase.UpdateWorkoutInitialDate
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CategoryListViewModel(
    private val getSportCategory: GetSportCategory,
    private val updateWorkoutInitialDate: UpdateWorkoutInitialDate
) : ViewModel() {

    suspend fun updateInitialDateIfNeeded() {
        updateWorkoutInitialDate()
    }

    fun provideCategories(): Flow<ImmutableList<SportCategory>> {
        return getSportCategory()
    }
}