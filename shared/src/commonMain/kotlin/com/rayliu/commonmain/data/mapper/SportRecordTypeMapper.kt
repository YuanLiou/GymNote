package com.rayliu.commonmain.data.mapper

import com.rayliu.commonmain.data.mapper.basic.Mapper
import com.rayliu.commonmain.domain.model.SportRecordType
import org.koin.core.annotation.Factory

@Factory
class SportRecordTypeMapper : Mapper<Int, SportRecordType> {
    override fun map(input: Int): SportRecordType {
        if (!SportRecordType.map.containsKey(input)) {
            return SportRecordType.UNKNOWN
        }
        return SportRecordType.map[input] ?: SportRecordType.UNKNOWN
    }
}