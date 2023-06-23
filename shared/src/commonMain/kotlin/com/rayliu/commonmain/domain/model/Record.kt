package com.rayliu.commonmain.domain.model

import kotlinx.datetime.LocalDateTime

interface Record {
    val id: Int
    val workoutRecordId: Int
    val sportRecordType: SportRecordType
    val createdAt: LocalDateTime
    val lastModified: LocalDateTime
}

data class WeightRepsRecord(
    override val id: Int,
    override val workoutRecordId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val weight: Float,
    val reps: Int
) : Record

data class WeightTimeRecord(
    override val id: Int,
    override val workoutRecordId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val weight: Float,
    val time: String
) : Record

data class DistanceTimeRecord(
    override val id: Int,
    override val workoutRecordId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime,
    val distance: Float,
    val time: String
) : Record

data class UnknownRecord(
    override val id: Int,
    override val workoutRecordId: Int,
    override val sportRecordType: SportRecordType,
    override val createdAt: LocalDateTime,
    override val lastModified: LocalDateTime
) : Record
