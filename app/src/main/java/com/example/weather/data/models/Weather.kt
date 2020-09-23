package com.example.weather.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Weather(val lat: Double, val lon: Double, val timezone: String, val timezone_offset: Int,
                   val current: Current, val minutely: List<Minutely>, val hourly: List<Hourly>, val daily: List<Daily>)