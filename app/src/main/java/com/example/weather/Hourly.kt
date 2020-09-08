package com.example.weather

data class Hourly(val dt: Int, val temp: Float, val feels_like: Float, val pressure: Int, val humidity: Int, val dew_point: Float,
val clouds: Int, val visibility: Int, val wind_speed: Float, val wind_deg: Int, val weather: List<WeatherItem>, val pop: Int)
