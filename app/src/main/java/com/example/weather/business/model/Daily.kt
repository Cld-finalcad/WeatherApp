package com.example.weather.business.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Daily (val dt:Int, val sunrise: Int, val sunset: Int, val temp: Temp, val feels_like: FeelsLike, val pressure: Int, val humidity: Int,
                  val dew_point: Double, val wind_speed: Double, val wind_deg: Int, val weather: List<WeatherItem>, val clouds: Int, val pop: Double, val uvi: Double)