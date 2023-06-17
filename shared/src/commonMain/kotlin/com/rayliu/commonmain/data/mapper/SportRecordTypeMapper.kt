package com.rayliu.commonmain.data.mapper

import com.rayliu.commonmain.data.mapper.basic.Mapper
import com.rayliu.commonmain.domain.model.SportsRecordType
import org.koin.core.annotation.Factory

@Factory
class SportRecordTypeMapper : Mapper<Int, SportsRecordType> {
    override fun map(input: Int): SportsRecordType {
        if (!SportsRecordType.map.containsKey(input)) {
            return SportsRecordType.UNKNOWN
        }
        return SportsRecordType.map[input] ?: SportsRecordType.UNKNOWN
    }
}