package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.SportCategory
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

fun interface GetSportCategory : () -> Flow<ImmutableList<SportCategory>>