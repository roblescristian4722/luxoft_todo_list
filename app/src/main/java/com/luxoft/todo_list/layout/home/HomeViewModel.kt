package com.luxoft.todo_list.layout.home

import com.luxoft.todo_list.layout.common.BaseViewModel
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter
import javax.inject.Inject

class HomeViewModel @Inject constructor(): BaseViewModel<HomeScreenEvents>() {
    fun onAddButtonPressed() {
        postEvent(HomeScreenEvents.AddButtonPressed)
    }

    fun editItem(position: Int, taskPresenter: TaskPresenter) {
        taskPresenter.apply {
            postEvent(HomeScreenEvents.EditItem(position, title, description))
        }
    }

    fun displayItem(presenter: TaskPresenter) {
        presenter.apply {
            postEvent(HomeScreenEvents.DisplayItem(id, title, description))
        }
    }
}