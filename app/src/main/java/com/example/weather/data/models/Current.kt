package com.example.weather.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Current (val dt:Int,
    val sunrise: Int, val sunset: Int, val temp: Double, val feels_like: Double, val pressure: Int, val humidity: Int,
    val dew_point: Double, val clouds: Int, val visibility: Int, val wind_speed: Double, val wind_deg: Int, val weather: List<WeatherItem>)

