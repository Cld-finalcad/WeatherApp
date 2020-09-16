package com.example.domain.usecases

import com.example.domain.models.Weather

interface WeatherUseCase {
    fun execute(): Weather
}