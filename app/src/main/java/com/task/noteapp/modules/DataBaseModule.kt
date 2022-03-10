package com.task.noteapp.modules

import android.app.Application
import androidx.room.Room
import com.task.noteapp.database.NoteDao
import com.task.noteapp.database.NoteDataBase
import com.task.noteapp.repo.DataBaseRepo
import com.task.noteapp.util.Constants
import org.koin.dsl.module

fun providesDatabase(application: Application): NoteDataBase =
    Room.databaseBuilder(application,NoteDataBase::class.java,Constants.DB_NAME)
        .build()



fun providesDao(db:NoteDataBase):NoteDao = db.getNoteDao()



val roomModule = module {

    single { providesDatabase(get()) }
    single { providesDao(get()) }
    single { DataBaseRepo(get()) }
}

val dbModule = listOf(roomModule)