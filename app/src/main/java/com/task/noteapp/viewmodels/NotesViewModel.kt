package com.task.noteapp.viewmodels

import androidx.lifecycle.*
import com.task.noteapp.model.Notes
import com.task.noteapp.repo.DataBaseRepo
import kotlinx.coroutines.launch


class NotesViewModel (private val noteDb: DataBaseRepo) : ViewModel() {

   private var listNotes = MutableLiveData<ArrayList<Notes>>()
   private var createdNote = MutableLiveData<Notes>()
   private var deletedNotes = MutableLiveData<List<Notes>>()



    fun delete(item: Notes)  = viewModelScope.launch {
        noteDb.delete(item)
    }

    fun deleteMark(item: Notes)  = viewModelScope.launch {
        noteDb.deleteMark(item)
    }


     fun createNotes(item: Notes)  = viewModelScope.launch{
        noteDb.createNotes(item)
    }


    fun update(item: Notes) = viewModelScope.launch{
        noteDb.update(item)
    }

    fun getNoteById(id: Int):LiveData<Notes> {
        viewModelScope.launch {
            createdNote.postValue(noteDb.getNoteById(id))
        }
        return createdNote
    }



    fun getAllNotes():LiveData<ArrayList<Notes>> {
        viewModelScope.launch {
            listNotes.postValue(noteDb.getList() as ArrayList<Notes>)
        }
        return listNotes

    }



}