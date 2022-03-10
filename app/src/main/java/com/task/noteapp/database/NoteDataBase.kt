package com.task.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.noteapp.model.Notes
import com.task.noteapp.util.Constants

@Database(entities = [Notes::class], version = Constants.version,exportSchema = true)
@TypeConverters(Converters::class)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}
