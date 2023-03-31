package com.luxoft.todo_list.layout.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.luxoft.todo_list.layout.recyclerview.presenters.BasePresenter

abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(presenter: BasePresenter, position: Int)
}