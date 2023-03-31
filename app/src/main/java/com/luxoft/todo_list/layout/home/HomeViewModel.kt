package com.luxoft.todo_list.layout.home

import com.luxoft.todo_list.layout.common.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(): BaseViewModel<HomeScreenEvents>() {
    fun onAddButtonPressed() {
        postEvent(HomeScreenEvents.AddButtonPressed)
    }

    fun editItem(position: Int) {
        postEvent(HomeScreenEvents.EditItem(position))
    }
}