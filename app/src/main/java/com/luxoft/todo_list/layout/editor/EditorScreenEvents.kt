package com.luxoft.todo_list.layout.editor

sealed class EditorScreenEvents {
    data class TitleIsEmpty(private val msg: String): EditorScreenEvents()
    data class DescriptionIsEmpty(private val msg: String): EditorScreenEvents()
    data class ErrorReadingFile(private val msg: String): EditorScreenEvents()
}