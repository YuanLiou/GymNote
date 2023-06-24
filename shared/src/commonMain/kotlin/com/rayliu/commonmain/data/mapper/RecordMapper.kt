package com.rayliu.commonmain.data.mapper

import com.rayliu.commonmain.data.database.DateTimeConverter
import com.rayliu.commonmain.data.database.RecordDetails
import com.rayliu.commonmain.data.mapper.basic.Mapper
import com.rayliu.commonmain.data.mapper.basic.NullableInputListMapper
import com.rayliu.commonmain.domain.model.DistanceTimeRecord
import com.rayliu.commonmain.domain.model.Record
import com.rayliu.commonmain.domain.model.SportRecordType
import com.rayliu.commonmain.domain.model.UnknownRecord
import com.rayliu.commonmain.domain.model.WeightRepsRecord
import com.rayliu.commonmain.domain.model.WeightTimeRecord
import org.koin.core.annotation.Factory

@Factory
class RecordMapper(
    private val sportRecordTypeMapper: SportRecordTypeMapper,
    private val dateTimeConverter: DateTimeConverter
) : Mapper<RecordDetails, Record> {
    override fun map(input: RecordDetails): Record {
        return when (val type = sportRecordTypeMapper.map(input.sportRecordTypeId.toInt())) {
            SportRecordType.WEIGHT_REPS -> WeightRepsRecord(
                id = input.id.toInt(),
                workoutId = input.workoutId.toInt(),
                sportRecordType = type,
                createdAt = dateTimeConverter.toLocalDateTime(input.createAt),
                lastModified = dateTimeConverter.toLocalDateTime(input.lastModified),
                weight = input.weight?.toFloat() ?: 0f,
                reps = input.reps?.toInt() ?: 0
            )
            SportRecordType.WEIGHT_TIME -> WeightTimeRecord(
                id = input.id.toInt(),
                workoutId = input.workoutId.toInt(),
                sportRecordType = type,
                createdAt = dateTimeConverter.toLocalDateTime(input.createAt),
                lastModified = dateTimeConverter.toLocalDateTime(input.lastModified),
                weight = input.weight?.toFloat() ?: 0f,
                time = input.time.orEmpty()
            )
            SportRecordType.DISTANCE_TIME -> DistanceTimeRecord(
                id = input.id.toInt(),
                workoutId = input.workoutId.toInt(),
                sportRecordType = type,
                createdAt = dateTimeConverter.toLocalDateTime(input.createAt),
                lastModified = dateTimeConverter.toLocalDateTime(input.lastModified),
                distance = input.distance?.toFloat() ?: 0f,
                time = input.time.orEmpty()
            )
            SportRecordType.UNKNOWN -> UnknownRecord(
                id = input.id.toInt(),
                workoutId = input.workoutId.toInt(),
                sportRecordType = type,
                createdAt = dateTimeConverter.toLocalDateTime(input.createAt),
                lastModified = dateTimeConverter.toLocalDateTime(input.lastModified)
            )
        }
    }
}

@Factory
class RecordListMapper(
    private val mapper: RecordMapper
) : NullableInputListMapper<RecordDetails, Record> {
    override fun map(input: List<RecordDetails>?): List<Record> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}

// Domain Object To DTO
@Factory
class RecordDetailsMapper : Mapper<Record, RecordDetails> {
    override fun map(input: Record): RecordDetails {
        val weight = when (input) {
            is WeightRepsRecord -> {
                input.weight
            }
            is WeightTimeRecord -> {
                input.weight
            }
            else -> {
                null
            }
        }

        val time = when (input) {
            is WeightTimeRecord -> {
                input.time
            }
            is DistanceTimeRecord -> {
                input.time
            }
            else -> {
                null
            }
        }

        return RecordDetails(
            id = -1,
            workoutId = input.workoutId.toLong(),
            createAt = input.createdAt.toString(),
            lastModified = input.lastModified.toString(),
            sportRecordTypeId = input.sportRecordType.id.toLong(),
            weight = weight?.toDouble(),
            reps = (input as? WeightRepsRecord)?.reps?.toLong(),
            time = time,
            distance = (input as? DistanceTimeRecord)?.distance?.toDouble()
        )
    }
}