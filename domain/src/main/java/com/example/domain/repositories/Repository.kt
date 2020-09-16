package com.example.domain.repositories

import com.example.domain.models.Weather

interface Repository {
    fun getWeather(): Weather
}