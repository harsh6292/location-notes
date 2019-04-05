package com.harshagg.pmrh.locationnotes.data.database

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toDateFromTimeStamp(timeStamp: Long?): Date? {
        return timeStamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestampFromDate(date: Date?): Long? {
        return date?.time
    }
}