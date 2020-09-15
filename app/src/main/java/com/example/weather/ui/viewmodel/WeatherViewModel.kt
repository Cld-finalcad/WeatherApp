package com.example.weather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.business.model.Weather
import com.example.weather.business.network.WeatherRepository
import kotlinx.coroutines.launch


class WeatherViewModel(
    private val repository: WeatherRepository = WeatherRepository.instance
) : ViewModel() {

    val lat = 45.75
    val lon = 4.85

    init {
        viewModelScope.launch {
            WeatherRepository.instance.refreshWeather(lat, lon)
        }
    }

    val weather: LiveData<Weather> = repository.getWeather(lat, lon)

}