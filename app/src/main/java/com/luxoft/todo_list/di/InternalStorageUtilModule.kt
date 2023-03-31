package com.luxoft.todo_list.di

import com.luxoft.todo_list.utils.InternalStorageUtil
import dagger.Module
import dagger.Provides

@Module
abstract class InternalStorageUtilModule {
    companion object {
        @Provides
        fun providesInternalStorageUtil(): InternalStorageUtil = InternalStorageUtil()
    }
}