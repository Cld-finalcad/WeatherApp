package com.example.weather.domain.repositories

import androidx.lifecycle.LiveData
import com.example.weather.domain.models.WeatherModelRaw

interface WeatherRepository {
    fun getWeather(lat: Double, lon: Double): LiveData<List<WeatherModelRaw>?>
    suspend fun refreshWeather(lat: Double, lon: Double)
}