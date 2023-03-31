package com.luxoft.todo_list.layout.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T>: ViewModel() {
    private val _event = MutableLiveData<Event<T>>()
    val event: LiveData<Event<T>>
        get() = _event

    fun postEvent(event: T) {
        _event.postValue(Event(event))
    }
}