package com.rayliu.commonmain.domain.model

import kotlinx.datetime.LocalDateTime

const val RECORD_EMPTY_ID = -1

sealed interface Record {
    val id: Int
    val workoutId: Int
    val sportRecordType: SportRecordType
    val createdAt: LocalDateTime
    val lastModified: LocalDateTime
}

data class WeightRepsRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val weight: Float,
    val reps: Int
) : Record

data class WeightTimeRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val weight: Float,
    val time: String
) : Record

data class DistanceTimeRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val distance: Float,
    val time: String
) : Record

data class WeightRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val weight: Float
) : Record

data class RepsRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val reps: Int
) : Record

data class TimeRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val time: String
) : Record

data class DistanceRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val distance: Float
) : Record

data class UnknownRecord(
    override val id: Int = RECORD_EMPTY_ID,
    override val workoutId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime
) : Record
