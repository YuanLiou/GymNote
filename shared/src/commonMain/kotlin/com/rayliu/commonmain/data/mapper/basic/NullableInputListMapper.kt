package com.rayliu.commonmain.data.mapper.basic

interface NullableInputListMapper<I, O> : Mapper<List<I>?, List<O>>

/*
sample implementation
class NullableInputListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableInputListMapper<I, O> {
    override fun map(input: List<I>?): List<O> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}
*/