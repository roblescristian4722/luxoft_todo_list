package com.luxoft.todo_list.layout.home

import com.luxoft.todo_list.layout.common.BaseViewModel

class HomeViewModel: BaseViewModel<HomeScreenEvents>() {
    fun onAddButtonPressed() {
        postEvent(HomeScreenEvents.AddButtonPressed)
    }
}