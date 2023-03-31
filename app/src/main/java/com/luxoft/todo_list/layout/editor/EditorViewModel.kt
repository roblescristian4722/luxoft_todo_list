package com.luxoft.todo_list.layout.editor

import com.luxoft.todo_list.layout.common.BaseViewModel
import java.io.FileOutputStream
import javax.inject.Inject

class EditorViewModel @Inject constructor(): BaseViewModel<EditorScreenEvents>() {
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