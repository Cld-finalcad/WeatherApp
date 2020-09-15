package com.example.weather.business.model

import com.example.weather.business.model.WeatherItem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Hourly(val dt: Int, val temp: Double, val feels_like: Double, val pressure: Int, val humidity: Int, val dew_point: Double,
                  val clouds: Int, val visibility: Int, val wind_speed: Double, val wind_deg: Int, val weather: List<WeatherItem>, val pop: Double)
