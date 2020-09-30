package com.example.weather.domain.repositories

import androidx.lifecycle.LiveData
import com.example.weather.domain.models.WeatherModelRaw
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(lat: Double, lon: Double): Flow<List<WeatherModelRaw>?>
    suspend fun refreshWeather(lat: Double, lon: Double)
}