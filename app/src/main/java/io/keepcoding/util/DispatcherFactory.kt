package io.keepcoding.util

import kotlin.coroutines.CoroutineContext

interface DispatcherFactory {

    fun getMain(): CoroutineContext
    fun getIO(): CoroutineContext

}