package io.keepcoding.util.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.keepcoding.util.Event

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(
    liveData: L,
    body: (T) -> Unit
) {
    liveData.observe(this, Observer(body))
}

fun MutableLiveData<Event<Unit>>.call() {
    value = Event(Unit)
}

fun Event<Unit>.consume(body: () -> Unit) {
    getContentIfNotHandled()?.let {
        body()
    }
}