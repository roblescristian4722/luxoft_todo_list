package com.luxoft.todo_list.di

import android.content.Context
import com.luxoft.todo_list.TodoListApplication
import com.luxoft.todo_list.layout.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(application: TodoListApplication)
    fun inject(homeFragment: HomeFragment)
}