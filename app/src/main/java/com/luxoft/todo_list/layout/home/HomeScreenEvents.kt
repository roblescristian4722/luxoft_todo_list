package com.luxoft.todo_list.layout.home

import com.luxoft.todo_list.layout.editor.EditorScreenEvents

sealed class HomeScreenEvents {
    object AddButtonPressed: HomeScreenEvents()
    data class EditItem(val position: Int): HomeScreenEvents()
}