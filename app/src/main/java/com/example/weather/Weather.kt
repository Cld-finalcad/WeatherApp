package com.example.weather

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(val lat: Float, val lon: Float, val timezone: String, val timezone_offset: Int,
                   val current: Current, val minutely: List<Minutely>, val hourly: List<Hourly>, val daily: List<Daily>)