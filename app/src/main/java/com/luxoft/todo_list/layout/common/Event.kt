package com.luxoft.todo_list.layout.common

import androidx.lifecycle.Observer

class Event<out T>(private val data: T) {
    private var dataHasBeenSet = false

    fun getData(): T? {
        if (dataHasBeenSet) {
            return null
        }
        dataHasBeenSet = true
        return data
    }

    class EventObserver<T>(private val callback: (event: T) -> Unit): Observer<Event<T>> {
        override fun onChanged(event: Event<T>) {
            event.getData()?.let { value ->
                callback(value)
            }
        }

    }
}