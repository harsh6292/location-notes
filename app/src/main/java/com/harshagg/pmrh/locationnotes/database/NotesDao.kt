package com.harshagg.pmrh.locationnotes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

@Dao
interface NotesDao {

    @Insert(onConflict = IGNORE)
    fun insertNote(note: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Update(onConflict = REPLACE)
    fun updateNote(note: NotesEntity)
}