package com.luxoft.todo_list.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luxoft.todo_list.databinding.ActivityMainBinding
import com.luxoft.todo_list.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Status("Launching Main Activity...")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}