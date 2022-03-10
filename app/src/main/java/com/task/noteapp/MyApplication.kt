package com.task.noteapp

import android.app.Application
import com.task.noteapp.modules.appModules
import com.task.noteapp.modules.dbModule
import com.zuper.todo.modules.viewModules


import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

         startKoin {
             androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(dbModule +viewModules)
        }
    }
}