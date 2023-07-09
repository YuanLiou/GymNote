package com.rayliu.gymnote.wearos.utils

object InputUtils {
    fun isNumeric(input: String): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return input.matches(regex)
    }
}