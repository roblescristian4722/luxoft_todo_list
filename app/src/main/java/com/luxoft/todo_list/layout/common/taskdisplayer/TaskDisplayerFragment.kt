package com.luxoft.todo_list.layout.common.taskdisplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luxoft.todo_list.R
import com.luxoft.todo_list.databinding.FragmentTaskDisplayerBinding
import com.luxoft.todo_list.utils.Alert

class TaskDisplayerFragment : Fragment() {
    private lateinit var binding: FragmentTaskDisplayerBinding
    private var id = -1
    private var title = ""
    private var description = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskDisplayerBinding.inflate(inflater, container, false)
        id = arguments?.getInt("id", -1) ?: -1
        title = arguments?.getString("title", "") ?: ""
        description = arguments?.getString("description", "") ?: ""
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (id != -1 && title.isNotEmpty() && description.isNotEmpty()) {
            binding.mtvId.text = getString(R.string.task_displayer_id, id.toString())
            binding.mtvTitle.text = title
            binding.mtvDescription.text = description
        }
    }
}