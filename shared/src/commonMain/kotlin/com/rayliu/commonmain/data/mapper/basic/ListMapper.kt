package com.rayliu.commonmain.data.mapper.basic

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

/*
sample implementation
class ListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : ListMapper<I, O> {
    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}
 */