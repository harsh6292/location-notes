package com.harshagg.pmrh.locationnotes.data.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_notes")
class NotesEntity {
    @PrimaryKey (autoGenerate = true)
    var noteId: Int = 0

    // The title of the note
    var noteTitleText: String

    // The actual note
    var noteBodyText: String

    // Date the note was last accessed
    var dateLastUpdated: Date

    constructor(noteId: Int, noteTitleText: String, noteBodyText: String, dateLastUpdated: Date) {
        this.noteId = noteId
        this.noteTitleText = noteTitleText
        this.noteBodyText = noteBodyText
        this.dateLastUpdated = dateLastUpdated
    }

    @Ignore
    constructor(title: String, body: String, date: Date) {
        this.noteTitleText = title
        this.noteBodyText = body
        this.dateLastUpdated = date
    }
}
