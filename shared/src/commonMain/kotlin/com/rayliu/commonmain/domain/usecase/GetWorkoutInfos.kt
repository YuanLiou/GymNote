package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.WorkoutInfo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface GetWorkoutInfos {
    operator fun invoke(categoryId: Int): Flow<ImmutableList<WorkoutInfo>>
}