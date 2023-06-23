package com.rayliu.commonmain.domain.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.LocalDateTime

data class SportRecord(
    val id: Int,
    val workoutId: Int,
    val records: ImmutableList<Record>,
    val createdAt: LocalDateTime
)