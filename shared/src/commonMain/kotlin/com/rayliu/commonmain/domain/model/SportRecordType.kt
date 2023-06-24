package com.rayliu.commonmain.domain.model

enum class SportRecordType(val id: Int) {
    WEIGHT_REPS(0),
    WEIGHT_TIME(1),
    DISTANCE_TIME(2),
    UNKNOWN(-1);

    companion object {
        val map = SportRecordType.values().associateBy { it.id }
    }
}