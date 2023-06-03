package com.rayliu.commonmain.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}