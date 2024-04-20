package com.rayliu.gymnote.wearos.categorylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayliu.commonmain.domain.usecase.GetSportCategory
import com.rayliu.commonmain.domain.usecase.UpdateWorkoutInitialDate
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CategoryListViewModel(
    getSportCategory: GetSportCategory,
    private val updateWorkoutInitialDate: UpdateWorkoutInitialDate
) : ViewModel() {
    val showProgress = mutableStateOf(false)
    val categoryListState =
        getSportCategory().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = persistentListOf()
        )

    fun performPreScreenTasks() {
        viewModelScope.launch {
            showProgress.value = true
            updateWorkoutInitialDate()
            showProgress.value = false
        }
    }
}