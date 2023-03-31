package com.luxoft.todo_list.layout.recyclerview.presenters

import com.luxoft.todo_list.models.Task

class TaskPresenter(other: Task): BasePresenter {
    val id: Int = other.id
    val title: String = other.title
    val description: String = other.description

    override fun areContentsTheSame(other: BasePresenter): Boolean {
        (other as TaskPresenter)
        if (other.id == id && other.title == title && other.description == description) {
            return true
        }
        return false
    }
}