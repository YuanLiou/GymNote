package com.rayliu.commonmain.data.mapper

import com.rayliu.commonmain.data.database.DateTimeConverter
import com.rayliu.commonmain.data.database.Workout
import com.rayliu.commonmain.data.mapper.basic.Mapper
import com.rayliu.commonmain.data.mapper.basic.NullableInputListMapper
import com.rayliu.commonmain.domain.model.WorkoutInfo
import org.koin.core.annotation.Factory

@Factory
class WorkoutInfoMapper(
    private val sportRecordTypeMapper: SportRecordTypeMapper,
    private val dateTimeConverter: DateTimeConverter
) : Mapper<Workout, WorkoutInfo> {
    override fun map(input: Workout): WorkoutInfo {
        val currentMoment = dateTimeConverter.provideCurrentMoment()
        return WorkoutInfo(
            id = input.id.toInt(),
            name = input.name,
            categoryId = input.categoryId.toInt(),
            sportRecordType = sportRecordTypeMapper.map(input.sportRecordTypeId.toInt()),
            createdAt = input.createAt?.run { dateTimeConverter.toLocalDateTime(this) }
                ?: currentMoment,
            lastModified = input.lastModified?.run { dateTimeConverter.toLocalDateTime(this) }
                ?: currentMoment
        )
    }
}

@Factory
class WorkoutInfoListMapper(
    private val mapper: WorkoutInfoMapper
) : NullableInputListMapper<Workout, WorkoutInfo> {
    override fun map(input: List<Workout>?): List<WorkoutInfo> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}
