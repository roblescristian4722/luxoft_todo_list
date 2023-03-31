package com.luxoft.todo_list.layout.editor

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.luxoft.todo_list.TodoListApplication
import com.luxoft.todo_list.databinding.FragmentEditorBinding
import com.luxoft.todo_list.layout.common.Event
import com.luxoft.todo_list.utils.Alert
import javax.inject.Inject

const val FILE_NAME = "output.csv"

class EditorFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: FragmentEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireContext().applicationContext as TodoListApplication).appComponent.inject(this)
        binding = FragmentEditorBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[EditorViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.writeToCSV(FILE_NAME, requireContext(), "ejemplo", "desc")
        val res = viewModel.readCSV(FILE_NAME, requireContext())
        Alert("res: $res")


        // Listeners
        binding.fabCancel.setOnClickListener { findNavController().popBackStack() }
        binding.fabDone.setOnClickListener {
            binding.apply {
                viewModel.doneBtnPressed(tietTitle.text.toString(), tietDescription.text.toString())
            }
        }

        // Screen event listener
        viewModel.event.observe(viewLifecycleOwner, Event.EventObserver { screenEvent ->

        })
    }
}