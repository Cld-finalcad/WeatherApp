package com.example.data.mappers

import com.example.data.models.WeatherData

class WeatherMappers {

    fun toWeather(weatherModelServer: WeatherData): Weather {
        return Weather (
            weatherModelServer.daily ?: emptyList()
        )
    }
}