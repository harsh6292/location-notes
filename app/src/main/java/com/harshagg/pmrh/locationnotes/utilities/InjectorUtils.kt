package com.harshagg.pmrh.locationnotes.utilities

import android.content.Context
import com.harshagg.pmrh.locationnotes.data.NotesRepository
import com.harshagg.pmrh.locationnotes.data.database.NotesDatabase
import com.harshagg.pmrh.locationnotes.ui.viewmodel.NotesActivityViewModelFactory

object InjectorUtils {

    fun provideRepository(context: Context): NotesRepository {
        val notesDatabase = NotesDatabase.getInstance(context.applicationContext)
        return NotesRepository.getInstance(notesDatabase.notesDao())
    }

    fun provideNotesActivityViewModelFactory(context: Context): NotesActivityViewModelFactory {
        val repository = provideRepository(context)
        return NotesActivityViewModelFactory(repository)
    }
}