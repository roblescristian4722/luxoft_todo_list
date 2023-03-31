
package com.luxoft.todo_list.layout.editor

import android.content.Context
import com.luxoft.todo_list.layout.common.BaseViewModel
import com.luxoft.todo_list.utils.Alert
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import javax.inject.Inject

class EditorViewModel @Inject constructor(): BaseViewModel<EditorScreenEvents>() {
    fun writeToCSV(outputFilename: String, context: Context, values: List<Pair<String, String>>) {
        val fout: FileOutputStream = context.openFileOutput(outputFilename, Context.MODE_PRIVATE)
        for (i in values) {
            fout.write("${i.first},${i.second}\n".toByteArray())
        }
        fout.close()
    }

    fun readCSV(inputFilename: String, context: Context): List<Pair<String, String>> {
        val res: MutableList<Pair<String, String>> = mutableListOf()
        try {
            var line: String? = ""
            val fin = context.openFileInput(inputFilename)
            val dis = DataInputStream(fin)
            val br = BufferedReader(InputStreamReader(dis))
            do {
                line = br.readLine()
                line.split(",").apply {
                    res.add(Pair(first(), last()))
                }
                Alert("line: $line")
            } while (!line.isNullOrEmpty())
            fin.close()
        } catch (e: Exception) {
            Alert("Error reading file: $e")
        }
        return res
    }

    fun doneBtnPressed(title: String, description: String) {
        if (title.isEmpty()) {
            postEvent(EditorScreenEvents.TitleIsEmpty("Title cannot be empty"))
            return
        }
        if (description.isEmpty()) {
            postEvent(EditorScreenEvents.TitleIsEmpty("Description cannot be empty"))
            return
        }
        try {

        } catch(e: Exception) {
            postEvent(EditorScreenEvents.ErrorReadingFile("Error reading file: $e"))
        }
    }
}