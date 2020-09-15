package com.example.weather.business.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class WeatherItem(val id: Int, val main: String, val description: String, val icon: String) {
}