package io.keepcoding.tareas.presentation

import androidx.lifecycle.ViewModel
import io.keepcoding.util.DispatcherFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(private val dispatcherFactory: DispatcherFactory) : ViewModel(), CoroutineScope {

    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcherFactory.getMain() + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}