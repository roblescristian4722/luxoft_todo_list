package com.luxoft.todo_list.layout.common

import androidx.lifecycle.Observer

class Event<out T>(private val content: T) {
    private var contentHasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        if (contentHasBeenHandled) {
            return null
        }
        contentHasBeenHandled = true
        return content
    }

    class EventObserver<T>(private val callback: (onEventUnhandled: T) -> Unit): Observer<Event<T>> {
        override fun onChanged(value: Event<T>) {
            value.getContentIfNotHandled()?.let {
                callback(it)
            }
        }
    }
}