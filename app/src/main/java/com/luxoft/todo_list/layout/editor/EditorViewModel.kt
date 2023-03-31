
package com.luxoft.todo_list.layout.editor

import android.content.Context
import com.luxoft.todo_list.layout.common.BaseViewModel
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter
import com.luxoft.todo_list.models.Task
import javax.inject.Inject

class EditorViewModel @Inject constructor(): BaseViewModel<EditorScreenEvents>() {
    fun doneBtnPressed(context: Context, title: String, description: String, edit: Int) {
        if (title.isEmpty()) {
            postEvent(EditorScreenEvents.TitleIsEmpty("Title cannot be empty"))
            return
        }
        if (description.isEmpty()) {
            postEvent(EditorScreenEvents.DescriptionIsEmpty("Description cannot be empty"))
            return
        }
        if (edit == -1) {
            readCSV(context).also {
                csv.value?.let {
                    val mutable = it.toMutableList()
                    val id = if (mutable.isEmpty()) 1 else mutable.last().id + 1
                    mutable.add(
                        TaskPresenter(
                            Task(
                                id,
                                title,
                                description
                            )
                        )
                    )
                    writeToCSV(context, mutable)
                } ?: run {
                    appendItem(
                        context, TaskPresenter(
                            Task(
                                1,
                                title,
                                description
                            )
                        )
                    )
                }
            }
        } else {
            readCSV(context).also { list ->
                csv.value?.let {
                    val mutable = list.toMutableList()
                    val id = mutable[edit].id
                    mutable[edit] = TaskPresenter(Task(id, title, description))
                    writeToCSV(context, mutable)
                }
            }
        }
        postEvent(EditorScreenEvents.TaskAdded)
    }
}