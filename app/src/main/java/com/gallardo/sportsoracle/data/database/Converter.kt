package com.gallardo.sportsoracle.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    @TypeConverter
    fun stringToDate(string: String): Date {
        return if (string.length == 8)
            SimpleDateFormat("yyyyMMdd").parse(string)
        else if (string.length == 5)
            SimpleDateFormat("HH:mm").parse(string)
        else
            Date()
    }

    @TypeConverter
    fun DateToString(date: Date): String {
        return date.toString()
    }
}
