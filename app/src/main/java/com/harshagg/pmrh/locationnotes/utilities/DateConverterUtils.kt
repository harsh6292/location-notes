package com.harshagg.pmrh.locationnotes.utilities

import java.util.*
import java.util.concurrent.TimeUnit

object DateConverterUtils {

    val normalizedUtcMsForToday: Long
        get() {
            val utcNowMillis = System.currentTimeMillis()
            val currentTimeZone = TimeZone.getDefault()
            val gmtOffsetMillis = currentTimeZone.getOffset(utcNowMillis).toLong()
            val timeSinceEpochLocalTimeMillis = utcNowMillis + gmtOffsetMillis
            val daysSinceEpochLocal = TimeUnit.MILLISECONDS.toDays(timeSinceEpochLocalTimeMillis)
            return TimeUnit.DAYS.toMillis(daysSinceEpochLocal)
        }

    val normalizedUtcDateForToday: Date
        get() {
            val normalizedMillis = normalizedUtcMsForToday
            return Date(normalizedMillis)
        }
}