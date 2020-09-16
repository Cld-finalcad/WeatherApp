package com.example.data.models

import androidx.room.Entity
import com.squareup.moshi.JsonClass

@Entity(primaryKeys = arrayOf("lat", "lon"))
@JsonClass(generateAdapter = true)

data class WeatherData(val lat: Double, val lon: Double, val timezone: String, val timezone_offset: Int,
                   val current: Current, val minutely: List<Minutely>, val hourly: List<Hourly>, val daily: List<Daily>)