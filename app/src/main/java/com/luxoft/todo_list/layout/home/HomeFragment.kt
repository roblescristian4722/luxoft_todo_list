package com.luxoft.todo_list.layout.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.luxoft.todo_list.R
import com.luxoft.todo_list.TodoListApplication
import com.luxoft.todo_list.databinding.FragmentHomeBinding
import com.luxoft.todo_list.layout.common.Event
import com.luxoft.todo_list.layout.recyclerview.AdapterType
import com.luxoft.todo_list.layout.recyclerview.DynamicAdapter
import com.luxoft.todo_list.layout.recyclerview.presenters.TaskPresenter
import com.luxoft.todo_list.layout.recyclerview.viewholders.TaskItemViewHolder
import com.luxoft.todo_list.utils.Alert
import javax.inject.Inject

class HomeFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireContext().applicationContext as TodoListApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.readCSV(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = DynamicAdapter<TaskPresenter, TaskItemViewHolder>(AdapterType.TASK_VERTICAL_LIST, viewModel)

        // Listeners
        binding.facAddTask.setOnClickListener { viewModel.onAddButtonPressed() }
        viewModel.csv.observe(viewLifecycleOwner) { list ->
            list?.let { adapter.submitList(it) }
        }

        // Screen event listener
        viewModel.event.observe(viewLifecycleOwner, Event.EventObserver { screenEvent ->
            when (screenEvent) {
                HomeScreenEvents.AddButtonPressed -> {
                    findNavController().navigate(R.id.action_homeFragment_to_editorFragment)
                }
            }
        })

        binding.rvTaskList.adapter = adapter
    }
}