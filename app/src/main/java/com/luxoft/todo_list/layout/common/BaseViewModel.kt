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

    companion object {
        protected val _csv = MutableLiveData<List<TaskPresenter>>()
        val csv: LiveData<List<TaskPresenter>>
            get() = _csv
    }

    fun postEvent(event: T) {
        _event.postValue(Event(event))
    }

    fun writeToCSV(context: Context, list: List<TaskPresenter>, append: Boolean = false) {
        val mode = if (append) Context.MODE_APPEND or Context.MODE_PRIVATE else Context.MODE_PRIVATE
        val fout: FileOutputStream = context.openFileOutput(FILE_NAME, mode)
        try {
            for (i in list) {
                fout.write("${i.id},${i.title},${i.description.replace("\n", "\\n")}\n".toByteArray())
            }
            _csv.postValue(list)
        } catch (e: Exception) {
            Alert("Error writing file: $e")
        }
        fout.close()
    }

    fun appendItem(context: Context, item: TaskPresenter) {
        val mutable = listOf(item)
        writeToCSV(context, mutable, true)
    }

    fun readCSV(context: Context): List<TaskPresenter> {
        val res: MutableList<TaskPresenter> = mutableListOf()
        try {
            var line: String? = ""
            val fin = context.openFileInput(FILE_NAME)
            val dis = DataInputStream(fin)
            val br = BufferedReader(InputStreamReader(dis))
            do {
                line = br.readLine()
                if (line == null) {
                    break
                }
                line.split(",").let {
                    res.add(
                        TaskPresenter(
                            Task(it[0].toInt(), it[1], it[2].replace("\\n", "\n"))
                        )
                    )
                }
            } while (!line.isNullOrEmpty())
            fin.close()
            Alert("res: ${res.map { it.id }}")
            _csv.postValue(res)
            return res
        } catch (e: Exception) {
            Alert("Error reading file: $e")
        }
        return listOf()
    }

    fun removeItem(context: Context, position: Int) {
        readCSV(context).let {
            if (position < it.size) {
                val mutable = it.toMutableList()
                mutable.removeAt(position)
                writeToCSV(context, mutable)
            }
        }
    }
}