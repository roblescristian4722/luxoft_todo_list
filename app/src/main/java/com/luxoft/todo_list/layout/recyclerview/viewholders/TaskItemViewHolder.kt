package com.luxoft.todo_list.layout.recyclerview.viewholders

import com.luxoft.todo_list.databinding.FragmentTaskItemBinding
import com.luxoft.todo_list.layout.home.HomeViewModel
import com.luxoft.todo_list.layout.recyclerview.presenters.BasePresenter
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter

class TaskItemViewHolder(private val binding: FragmentTaskItemBinding, private val viewModel: HomeViewModel): BaseViewHolder(binding.root) {
    override fun bind(presenter: BasePresenter, position: Int) {
        presenter as TaskPresenter
        binding.mtvTitle.text = presenter.title

        binding.fabDelete.setOnClickListener {
//            viewModel.removeItem(binding.root.context, position)
        }

        binding.efabEdit.setOnClickListener {

        }
    }

}