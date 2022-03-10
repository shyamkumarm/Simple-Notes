package com.task.noteapp.repo

import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import com.task.noteapp.database.NoteDao
import com.task.noteapp.model.Notes

class DataBaseRepo(private val dao: NoteDao) {



    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun getList()= dao.getAll()

    @WorkerThread
    suspend fun delete(item: Notes) =
        dao.delete(item)

    @WorkerThread
    suspend fun deleteMark(item: Notes) =
        dao.deleteMark(item.id)


    @WorkerThread
    suspend  fun createNotes(item: Notes) {
        dao.insertAll(item)
    }

    @WorkerThread
    suspend fun getNoteById(item: Int):Notes? {
       return dao.getNoteById(item)
    }

    @WorkerThread
    suspend fun update(item: Notes) {
        dao.update(item)
    }
}