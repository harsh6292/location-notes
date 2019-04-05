package com.harshagg.pmrh.locationnotes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshagg.pmrh.locationnotes.data.NotesRepository

class NotesActivityViewModelFactory(private val mRepository: NotesRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesActivityViewModel(mRepository) as T
    }
}