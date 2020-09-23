package com.example.weather.domain.models

data class WeatherModel (
    val timezone: String,
    val date: String,
    val main: String,
    val iconURL: String,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val wind: Double,
    val flags: Array<Flag>
)

enum class Flag {
    SUNNY, COLD, CHILLY, WINDY
}