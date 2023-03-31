package com.luxoft.todo_list

import android.app.Application
import com.luxoft.todo_list.di.AppComponent
import com.luxoft.todo_list.di.DaggerAppComponent

class TodoListApplication: Application() {
    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }
}