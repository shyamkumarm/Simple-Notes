package com.task.noteapp.database

import androidx.room.*
import com.task.noteapp.model.Notes
import com.task.noteapp.model.Tag

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
   suspend fun getAll(): List<Notes>

   @Query("SELECT * FROM Notes where id =:id")
   suspend  fun getNoteById(id:Int): Notes?

    @Insert
    suspend fun insertAll(vararg notes: Notes)

    @Update
  suspend  fun update(note: Notes)

    @Delete
   suspend fun delete(note: Notes)

    @Query("UPDATE Notes SET tag =:delete WHERE id = :id")
   suspend fun deleteMark(id: Int,delete:Tag = Tag.DELETED)
}
