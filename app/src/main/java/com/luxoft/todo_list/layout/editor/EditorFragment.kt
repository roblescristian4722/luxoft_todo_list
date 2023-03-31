package com.luxoft.todo_list.layout.editor

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
import javax.inject.Inject

class EditorFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: FragmentEditorBinding
    private var edit = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireContext().applicationContext as TodoListApplication).appComponent.inject(this)
        binding = FragmentEditorBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[EditorViewModel::class.java]
        edit = arguments?.getInt("position", -1) ?: -1
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Listeners
        binding.fabCancel.setOnClickListener { findNavController().popBackStack() }
        binding.fabDone.setOnClickListener {
            binding.apply {
                viewModel.doneBtnPressed(requireContext(), tietTitle.text.toString(), tietDescription.text.toString(), edit)
            }
        }

        // Screen event listener
        viewModel.event.observe(viewLifecycleOwner, Event.EventObserver { screenEvent ->
            when (screenEvent) {
                is EditorScreenEvents.DescriptionIsEmpty -> {
                    binding.tietDescription.error = screenEvent.msg
                }
                is EditorScreenEvents.ErrorReadingFile -> {

                }
                is EditorScreenEvents.TitleIsEmpty -> {
                    binding.tietTitle.error = screenEvent.msg
                }
                EditorScreenEvents.TaskAdded -> {
                    findNavController().popBackStack()
                }
            }
        })
    }
}