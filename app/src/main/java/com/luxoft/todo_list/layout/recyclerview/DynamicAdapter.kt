package com.luxoft.todo_list.layout.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.luxoft.todo_list.layout.recyclerview.presenters.BasePresenter
import com.luxoft.todo_list.layout.recyclerview.viewholders.BaseViewHolder

class DiffUtilCallback<P: BasePresenter>: DiffUtil.ItemCallback<P>() {
    override fun areItemsTheSame(oldItem: P, newItem: P): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: P, newItem: P): Boolean = oldItem.areContentsTheSame(newItem)
}

enum class AdapterType {
    TASK_VERTICAL_LIST
}

class DynamicAdapter<P: BasePresenter, VH: BaseViewHolder>(private val type: AdapterType): ListAdapter<P, VH>(DiffUtilCallback<P>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        when (type) {
            AdapterType.TASK_VERTICAL_LIST -> {

            }
        }
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        TODO("Not yet implemented")
    }
}