package com.luxoft.todo_list.layout.recyclerview.presenters

interface BasePresenter {
    fun areContentsTheSame(other: BasePresenter): Boolean
}