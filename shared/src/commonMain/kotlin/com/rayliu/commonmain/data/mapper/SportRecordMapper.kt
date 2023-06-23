package com.rayliu.commonmain.data.mapper

import com.rayliu.commonmain.data.database.DateTimeConverter
import com.rayliu.commonmain.data.database.WorkoutRecord
import com.rayliu.commonmain.data.mapper.basic.Mapper
import com.rayliu.commonmain.data.mapper.basic.NullableInputListMapper
import com.rayliu.commonmain.domain.model.Record
import com.rayliu.commonmain.domain.model.SportRecord
import kotlinx.collections.immutable.ImmutableList
import org.koin.core.annotation.Factory

@Factory
class SportRecordMapper(
    private val records: ImmutableList<Record>,
    private val dateTimeConverter: DateTimeConverter
) : Mapper<WorkoutRecord, SportRecord> {
    override fun map(input: WorkoutRecord): SportRecord {
        return SportRecord(
            id = input.id.toInt(),
            workoutId = input.workoutId.toInt(),
            records = records,
            createdAt = dateTimeConverter.toLocalDateTime(input.createAt)
        )
    }
}

@Factory
class SportRecordListMapper(
    private val mapper: SportRecordMapper
) : NullableInputListMapper<WorkoutRecord, SportRecord> {
    override fun map(input: List<WorkoutRecord>?): List<SportRecord> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}
