package com.rayliu.commonmain.domain.usecase

import com.rayliu.commonmain.domain.model.DistanceTimeRecord
import com.rayliu.commonmain.domain.model.Record
import com.rayliu.commonmain.domain.model.SportRecordType
import com.rayliu.commonmain.domain.model.WeightRepsRecord
import com.rayliu.commonmain.domain.model.WeightTimeRecord
import com.rayliu.commonmain.domain.repository.SportRecordRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.annotation.Factory

class GetSportRecordsImpl(
    private val sportRecordRepository: SportRecordRepository
) : GetSportRecords {
    override fun invoke(workoutId: Int): Flow<ImmutableList<Record>> {
        if (workoutId == -1) {
            return emptyFlow()
        }
        return sportRecordRepository.getSportRecords(workoutId)
    }
}

@Factory
class MockGetSportRecordsImpl(
    private val sportRecordRepository: SportRecordRepository
) : GetSportRecords {
    override fun invoke(workoutId: Int): Flow<ImmutableList<Record>> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return flowOf(
            persistentListOf(
                WeightRepsRecord(
                    id = 7323,
                    workoutId = 6431,
                    sportRecordType = SportRecordType.UNKNOWN,
                    createdAt = today,
                    lastModified = today,
                    weight = 12f,
                    reps = 8
                ),
                WeightRepsRecord(
                    id = 7324,
                    workoutId = 6432,
                    sportRecordType = SportRecordType.UNKNOWN,
                    createdAt = today,
                    lastModified = today,
                    weight = 14f,
                    reps = 12
                ),
                WeightRepsRecord(
                    id = 7321,
                    workoutId = 6434,
                    sportRecordType = SportRecordType.UNKNOWN,
                    createdAt = today,
                    lastModified = today,
                    weight = 21f,
                    reps = 14
                ),
                WeightTimeRecord(
                    id = 3449,
                    workoutId = 3429,
                    sportRecordType = SportRecordType.UNKNOWN,
                    createdAt = today,
                    lastModified = today,
                    weight = 2.3f,
                    time = "05:00"
                ),
                WeightRepsRecord(
                    id = 7325,
                    workoutId = 6427,
                    sportRecordType = SportRecordType.UNKNOWN,
                    createdAt = today,
                    lastModified = today,
                    weight = 28f,
                    reps = 6
                ),
                DistanceTimeRecord(
                    id = 7040,
                    workoutId = 1223,
                    sportRecordType = SportRecordType.UNKNOWN,
                    createdAt = today,
                    lastModified = today,
                    distance = 4.5f,
                    time = "24:00"
                )
            )
        )
    }
}
