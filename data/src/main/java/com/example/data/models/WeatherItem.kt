package com.example.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class WeatherItem(val id: Int, val main: String, val description: String, val icon: String) {
}