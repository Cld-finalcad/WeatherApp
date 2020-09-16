package com.example.domain.usecases

import com.example.domain.models.Weather

class GetWeatherUseCase (repo: Respository) : WeatherUseCase {

    override fun execute(): Weather {
        return repo.getWeather()
    }
}