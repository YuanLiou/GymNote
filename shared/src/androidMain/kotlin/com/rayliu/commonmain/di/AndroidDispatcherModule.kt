package com.rayliu.commonmain.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val androidDispatcherModule =
    module {
        includes(dispatcherModule)
        factory<CoroutineDispatcher>(named("Main")) {
            Dispatchers.Main
        }
    }