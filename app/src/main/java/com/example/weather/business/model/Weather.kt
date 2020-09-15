package com.example.weather.business.model

import androidx.room.Entity
import com.example.weather.business.model.Current
import com.example.weather.business.model.Daily
import com.example.weather.business.model.Hourly
import com.example.weather.business.model.Minutely
import com.squareup.moshi.JsonClass

@Entity(primaryKeys = arrayOf("lat", "lon"))
@JsonClass(generateAdapter = true)

data class Weather(val lat: Double, val lon: Double, val timezone: String, val timezone_offset: Int,
                   val current: Current, val minutely: List<Minutely>, val hourly: List<Hourly>, val daily: List<Daily>)