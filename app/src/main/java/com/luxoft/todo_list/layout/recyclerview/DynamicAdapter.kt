package com.luxoft.todo_list.layout.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.luxoft.todo_list.databinding.FragmentTaskItemBinding
import com.luxoft.todo_list.layout.common.BaseViewModel
import com.luxoft.todo_list.layout.home.HomeViewModel
import com.luxoft.todo_list.layout.recyclerview.presenters.BasePresenter
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter
import com.luxoft.todo_list.layout.recyclerview.viewholders.BaseViewHolder
import com.luxoft.todo_list.layout.recyclerview.viewholders.TaskItemViewHolder

class DiffUtilCallback<P: BasePresenter>: DiffUtil.ItemCallback<P>() {
    override fun areItemsTheSame(oldItem: P, newItem: P): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: P, newItem: P): Boolean = oldItem.areContentsTheSame(newItem)
}

enum class AdapterType {
    TASK_VERTICAL_LIST
}

class DynamicAdapter<P: BasePresenter, VH: BaseViewHolder>(
    private val type: AdapterType,
    private val viewModel: BaseViewModel<*>
    ): ListAdapter<P, VH>(DiffUtilCallback<P>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        when (type) {
            AdapterType.TASK_VERTICAL_LIST -> {
                val binding = FragmentTaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return TaskItemViewHolder(binding, viewModel as HomeViewModel) as VH
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (currentList.isNotEmpty()) {
            when (type) {
                AdapterType.TASK_VERTICAL_LIST -> {
                    holder as TaskItemViewHolder
                    val presenter = currentList[position] as TaskPresenter
                    holder.bind(presenter, position)
                }
            }
        }
    }
}