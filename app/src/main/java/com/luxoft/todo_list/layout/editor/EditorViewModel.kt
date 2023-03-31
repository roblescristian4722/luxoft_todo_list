
package com.luxoft.todo_list.layout.editor

import android.content.Context
import com.luxoft.todo_list.layout.common.BaseViewModel
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter
import com.luxoft.todo_list.models.Task
import com.luxoft.todo_list.utils.Alert
import javax.inject.Inject

class EditorViewModel @Inject constructor(): BaseViewModel<EditorScreenEvents>() {
    fun doneBtnPressed(context: Context, title: String, description: String) {
        if (title.isEmpty()) {
            postEvent(EditorScreenEvents.TitleIsEmpty("Title cannot be empty"))
            return
        }
        if (description.isEmpty()) {
            postEvent(EditorScreenEvents.DescriptionIsEmpty("Description cannot be empty"))
            return
        }
        appendItem(context, TaskPresenter(Task(
            1,
            title,
            description
        )))
        postEvent(EditorScreenEvents.TaskAdded)
    }
}