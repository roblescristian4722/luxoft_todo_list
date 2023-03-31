package com.luxoft.todo_list.layout.home

import com.luxoft.todo_list.layout.common.BaseViewModel
import com.luxoft.todo_list.utils.Alert
import javax.inject.Inject

class HomeViewModel @Inject constructor(): BaseViewModel<HomeScreenEvents>() {
    fun onAddButtonPressed() {
        postEvent(HomeScreenEvents.AddButtonPressed)
    }

    fun testing() {
        Alert("testing...")
    }
}