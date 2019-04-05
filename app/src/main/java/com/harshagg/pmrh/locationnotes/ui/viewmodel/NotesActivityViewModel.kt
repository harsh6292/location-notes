package com.harshagg.pmrh.locationnotes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.harshagg.pmrh.locationnotes.data.NotesRepository
import com.harshagg.pmrh.locationnotes.data.database.NotesEntity

class NotesActivityViewModel(private val mRepository: NotesRepository) : ViewModel() {

    private val mAllNotes = mRepository.getAllNotes()

    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return mAllNotes
    }
}