package com.gallardo.sportsoracle.data.database

import android.util.Log
import androidx.room.TypeConverter
import com.gallardo.sportsoracle.view.toString
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    @TypeConverter
    fun stringToDate(string: String): Date {
        return when (string.length) {
            12 -> SimpleDateFormat("yyyyMMddHHmm").parse(string)
            else -> Date()
        }
    }

    @TypeConverter
    fun dateToString(date: Date): String {
        return date.toString("yyyyMMddHHmm")
    }
}
