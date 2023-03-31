package com.luxoft.todo_list.layout.home

import com.luxoft.todo_list.layout.common.BaseViewModel
import com.luxoft.todo_list.utils.Alert
import com.luxoft.todo_list.utils.InternalStorageUtil
import javax.inject.Inject

class HomeViewModel @Inject constructor(): BaseViewModel<HomeScreenEvents>() {
    @Inject
    lateinit var internalStorageUtil: InternalStorageUtil
    fun onAddButtonPressed() {
//        postEvent(HomeScreenEvents.AddButtonPressed)
        Alert("data: ${internalStorageUtil.data}")
    }
}