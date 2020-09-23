package com.example.weather.data.database.models

import androidx.room.Entity
import com.squareup.moshi.JsonClass


@Entity(primaryKeys = arrayOf("lat", "lon", "date"))
@JsonClass(generateAdapter = true)

data class WeatherModelDB (
    val lat: Double,
    val lon: Double,
    val timestamp: Long,
    val timezone: String,
    val date: String,
    val main: String,
    val iconURL: String,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double
)