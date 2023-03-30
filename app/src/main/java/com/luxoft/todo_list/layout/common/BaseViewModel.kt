package com.luxoft.todo_list.layout.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T>: ViewModel() {
    private val _event = MutableLiveData<Event<T>>()

    fun postEvent(event: T) {
        _event.postValue(Event(event))
    }
}