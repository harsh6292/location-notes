package com.harshagg.pmrh.locationnotes.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_notes")
data class NotesEntity (
    @PrimaryKey (autoGenerate = true) var noteId: Int,

    // The title of the note
    var noteTitleText: String?,

    // The actual note
    var noteBodyText: String?,

    // Date the note was last accessed
    var dateLastUpdated: Date
)
