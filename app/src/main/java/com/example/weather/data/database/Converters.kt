package com.example.weather.data.database

import androidx.room.TypeConverter
import com.example.weather.data.models.Current
import com.example.weather.data.models.Daily
import com.example.weather.data.models.Hourly
import com.example.weather.data.models.Minutely
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class Converters {
    @TypeConverter
    fun dateToTimeStamp(date: Date): Long = date.time

    @TypeConverter
    fun timeStampToDate(long: Long): Date = long.let { Date(it) }

}