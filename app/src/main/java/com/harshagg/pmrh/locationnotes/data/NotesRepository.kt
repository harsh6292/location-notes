package com.harshagg.pmrh.locationnotes.data

import androidx.lifecycle.LiveData
import com.harshagg.pmrh.locationnotes.data.database.NotesDao
import com.harshagg.pmrh.locationnotes.data.database.NotesEntity
import java.util.concurrent.Executors

class NotesRepository private constructor(private val mNotesDao: NotesDao) {

    companion object {
        @Volatile
        private var repoInstance: NotesRepository? = null

        private val LOCK = Any()

        fun getInstance(notesDao: NotesDao): NotesRepository {
            return repoInstance ?: synchronized(LOCK) {
                val instance = NotesRepository(notesDao)
                repoInstance = instance
                return instance
            }
        }
    }

    private val executor = Executors.newSingleThreadExecutor()

    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return mNotesDao.getAllNotes()
    }

    fun insertNote(notesEntity: NotesEntity) {
        executor.execute(Runnable {
            mNotesDao.insertNote(notesEntity)
        })
    }

    fun getNoteById(noteId: Int): NotesEntity {
        return mNotesDao.getNoteById(noteId)
    }
}