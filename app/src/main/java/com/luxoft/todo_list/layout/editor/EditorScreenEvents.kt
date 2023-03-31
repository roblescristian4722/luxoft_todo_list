package com.luxoft.todo_list.layout.editor

sealed class EditorScreenEvents {
    data class TitleIsEmpty(val msg: String): EditorScreenEvents()
    data class DescriptionIsEmpty(val msg: String): EditorScreenEvents()
    data class ErrorReadingFile(val msg: String): EditorScreenEvents()
    object TaskAdded: EditorScreenEvents()
}