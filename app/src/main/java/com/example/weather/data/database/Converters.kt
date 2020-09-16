package com.example.weather.data.database

import androidx.room.TypeConverter
import com.example.weather.data.models.Current
import com.example.weather.data.models.Daily
import com.example.weather.data.models.Hourly
import com.example.weather.data.models.Minutely
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun currentToString(current: Current): String = Gson().toJson(current)

    @TypeConverter
    fun stringToCurrent(string: String): Current = Gson().fromJson(string, Current::class.java)

    @TypeConverter
    fun minutelyListToString(minutely: List<Minutely>): String = Gson().toJson(minutely)

    @TypeConverter
    fun stringToMinutelyList(string: String): List<Minutely> {

        val listType: Type =
            object : TypeToken<List<Minutely>>() {}.type

        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun hourlyListToString(hourly: List<Hourly>): String = Gson().toJson(hourly)

    @TypeConverter
    fun stringToHourlyList(string: String): List<Hourly> {
        val listType: Type =
            object : TypeToken<List<Hourly>>() {}.type

        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun dailyListToString(daily: List<Daily>): String = Gson().toJson(daily)

    @TypeConverter
    fun stringTodailyList(string: String): List<Daily> {
        val listType: Type =
            object : TypeToken<List<Daily>>() {}.type

        return Gson().fromJson(string, listType)
    }

}