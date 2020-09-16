package com.example.weather.domain.repositories

import androidx.lifecycle.LiveData
import com.example.weather.data.network.Webservice
import com.example.weather.domain.models.WeatherModel
import javax.inject.Inject

interface WeatherRepository {
    fun getWeather(lat: Double, lon: Double): LiveData<List<WeatherModel>>
    suspend fun refreshWeather(lat: Double, lon: Double)
}