package com.zuper.todo.modules


import com.task.noteapp.viewmodels.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module





val requestViewModel = module  {
    viewModel{ NotesViewModel(get()) }

}

val viewModules = listOf(requestViewModel)

