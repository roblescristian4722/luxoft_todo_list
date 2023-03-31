package com.luxoft.todo_list.layout.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.luxoft.todo_list.TodoListApplication
import com.luxoft.todo_list.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireContext().applicationContext as TodoListApplication).appComponent.inject(this)
        val vm = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        vm.testing()
        return binding.root
    }
}