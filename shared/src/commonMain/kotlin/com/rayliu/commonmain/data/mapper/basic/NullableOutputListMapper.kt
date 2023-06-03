package com.rayliu.commonmain.data.mapper.basic

interface NullableOutputListMapper<I, O> : Mapper<List<I>, List<O>?>

/*
sample implementation
class NullableOutputListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableOutputListMapper<I, O> {
    override fun map(input: List<I>): List<O>? {
        return if (input.isEmpty()) {
            return null
        } else {
            input.map { mapper.map(it) }
        }
    }
}
 */