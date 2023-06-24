package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.Record
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

fun interface GetSportRecords : (Int) -> Flow<ImmutableList<Record>>