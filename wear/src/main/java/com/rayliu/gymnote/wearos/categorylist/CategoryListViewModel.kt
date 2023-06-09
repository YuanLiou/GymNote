package com.rayliu.gymnote.wearos.categorylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.commonmain.domain.usecase.GetSportCategory
import com.rayliu.commonmain.domain.usecase.UpdateWorkoutInitialDate
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CategoryListViewModel(
    private val getSportCategory: GetSportCategory,
    private val updateWorkoutInitialDate: UpdateWorkoutInitialDate
) : ViewModel() {

    val showProgress = mutableStateOf(false)

    fun performPreScreenTasks() {
        viewModelScope.launch {
            showProgress.value = true
            updateWorkoutInitialDate()
            showProgress.value = false
        }
    }

    fun provideCategories(): Flow<ImmutableList<SportCategory>> {
        return getSportCategory()
    }
}