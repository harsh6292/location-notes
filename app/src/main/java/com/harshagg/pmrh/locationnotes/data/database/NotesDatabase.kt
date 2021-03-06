package com.harshagg.pmrh.locationnotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harshagg.pmrh.locationnotes.utilities.DATABASE_NAME

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object {
        @Volatile
        private var databaseInstance: NotesDatabase? = null


        fun getInstance(context: Context): NotesDatabase {
            return databaseInstance ?: synchronized(this) {
                val instance = buildDatabase(context)
                databaseInstance = instance
                return instance
            }
        }

        private fun buildDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME)
                   .build()
        }
    }
}
