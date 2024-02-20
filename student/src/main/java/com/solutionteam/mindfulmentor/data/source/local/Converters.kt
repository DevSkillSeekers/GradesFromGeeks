package com.solutionteam.mindfulmentor.data.source.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.Date

@ProvidedTypeConverter
class Converters(val gson: Gson) {

    @TypeConverter
    fun dateToLong(date: Date): Long = date.time

    @TypeConverter
    fun longToDate(long: Long): Date = Date(long)
}
