package com.example.weather.domain.usecases

import androidx.lifecycle.LiveData
import com.example.weather.data.network.WeatherRepositoryImpl
import com.example.weather.domain.models.WeatherModel
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repo: WeatherRepositoryImpl)
{

     fun getWeather(lat: Double, lon: Double): LiveData<List<WeatherModel>> {
        return repo.getWeather(lat, lon)
    }
}