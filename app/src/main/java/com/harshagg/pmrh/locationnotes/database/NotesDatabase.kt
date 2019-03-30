package com.harshagg.pmrh.locationnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harshagg.pmrh.locationnotes.utilities.DATABASE_NAME

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object {
        @Volatile
        private var databaseInstance: NotesDatabase? = null


        fun getInstance(context: Context): NotesDatabase {
            return databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME)
                   .build()
        }
    }
}
