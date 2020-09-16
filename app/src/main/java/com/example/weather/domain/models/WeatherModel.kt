package com.example.weather.domain.models

data class WeatherModel (
    val timezone: String,
    val date: Int,
    val main: String,
    val iconURL: String,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double

)