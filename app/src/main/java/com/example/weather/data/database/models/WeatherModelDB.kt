package com.example.weather.data.database.models

import androidx.room.Entity
import java.util.*


@Entity(tableName = "week_weather", primaryKeys = arrayOf("lat", "lon", "date"))

data class WeatherModelDB (
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val date: Date,
    val main: String,
    val iconURL: String,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double
)