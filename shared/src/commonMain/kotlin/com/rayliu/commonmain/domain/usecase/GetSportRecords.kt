package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.Record
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface GetSportRecords {
    operator fun invoke(workoutId: Int): Flow<ImmutableList<Record>>
}