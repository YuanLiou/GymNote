package com.rayliu.commonmain.domain.model

import kotlinx.datetime.LocalDateTime

data class WorkoutInfo(
    val id: Int,
    val name: String,
    val categoryId: Int,
    val sportRecordType: SportRecordType,
    val createdAt: LocalDateTime,
    val lastModified: LocalDateTime
)
