package com.harshagg.pmrh.locationnotes.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.harshagg.pmrh.locationnotes.data.database.NotesEntity
import com.harshagg.pmrh.locationnotes.utilities.InjectorUtils
import java.util.*
import java.util.concurrent.Executors

class NewNoteFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository = InjectorUtils.provideRepository(application)

    private val mSingleNoteEntry = MutableLiveData<NotesEntity>()

    private val executor = Executors.newSingleThreadExecutor()

    fun getNote(): MutableLiveData<NotesEntity> {
        return mSingleNoteEntry
    }

    fun loadNote(noteId: Int) {
        executor.execute(Runnable {
            val note = mRepository.getNoteById(noteId)

            mSingleNoteEntry.postValue(note)
        })
    }

    fun createNote(titleText: String, message: String, date: Date) {
        val newNote = NotesEntity(titleText, message, date)
        mSingleNoteEntry.postValue(newNote)
    }

    fun saveNote() {
        mSingleNoteEntry.value?.let {
            mRepository.insertNote(it)
        }
    }
}