package com.luxoft.todo_list.utils

import android.content.Context
import com.luxoft.todo_list.layout.common.FILE_NAME
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter
import com.luxoft.todo_list.models.Task
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InternalStorageUtil @Inject constructor() {
    var data: List<TaskPresenter> = listOf()

    fun writeToCSV(context: Context, append: Boolean = false) {
        val mode = if (append) Context.MODE_APPEND or Context.MODE_PRIVATE else Context.MODE_PRIVATE
        val fout: FileOutputStream = context.openFileOutput(FILE_NAME, mode)
        try {
            for (i in data) {
                fout.write("${i.id},${i.title},${i.description.replace("\n", "\\n")}\n".toByteArray())
            }
        } catch (e: Exception) {
            Alert("Error writing file: $e")
        }
        fout.close()
    }

    fun appendItem(context: Context, item: TaskPresenter) {
        val mutable = listOf(item)
        writeToCSV(context, true)
    }

    fun readCSV(context: Context) {
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
            data = res
        } catch (e: Exception) {
            Alert("Error reading file: $e")
        }
    }

    fun removeItem(context: Context, position: Int) {
        if (position < data.size) {
            val mutable = data.toMutableList()
            mutable.removeAt(position)
            data = mutable
            writeToCSV(context)
        }
    }
}