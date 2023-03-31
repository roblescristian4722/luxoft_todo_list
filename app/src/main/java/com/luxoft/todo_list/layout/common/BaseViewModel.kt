package com.luxoft.todo_list.layout.common

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter
import com.luxoft.todo_list.models.Task
import com.luxoft.todo_list.utils.Alert
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

const val FILE_NAME = "output.csv"

open class BaseViewModel<T>: ViewModel() {
    private val _event = MutableLiveData<Event<T>>()
    val event: LiveData<Event<T>>
        get() = _event

    protected val _csv = MutableLiveData<List<TaskPresenter>>()
    val csv: LiveData<List<TaskPresenter>>
        get() = _csv

    fun postEvent(event: T) {
        _event.postValue(Event(event))
    }


}