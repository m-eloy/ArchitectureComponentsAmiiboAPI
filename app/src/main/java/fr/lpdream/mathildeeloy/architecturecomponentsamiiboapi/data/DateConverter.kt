package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDate(date: Date?): Long = date?.time ?: 0

    @TypeConverter
    fun toDate(time: Long?): Date = if (time != null) Date(time) else Date()
}