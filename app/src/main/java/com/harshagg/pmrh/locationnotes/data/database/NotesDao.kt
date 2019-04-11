package com.harshagg.pmrh.locationnotes.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NotesDao {

    @Insert(onConflict = IGNORE)
    fun insertNote(note: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Update(onConflict = REPLACE)
    fun updateNote(note: NotesEntity)

    @Query("SELECT * from user_notes")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Query("SELECT * from user_notes where noteId = :inputId")
    fun getNoteById(inputId: Int): NotesEntity
}