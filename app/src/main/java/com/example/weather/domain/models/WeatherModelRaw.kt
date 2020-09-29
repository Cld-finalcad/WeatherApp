package com.example.weather.domain.models

import java.util.*

data class WeatherModelRaw (
    val timezone: String,
    val date: Date,
    val main: String,
    val iconURL: String,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double
)