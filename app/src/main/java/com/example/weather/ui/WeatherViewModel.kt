package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.business.Weather
import com.example.weather.business.WeatherRepository
import kotlinx.coroutines.launch


class WeatherViewModel (val weather: LiveData<Weather> =
                            WeatherRepository.instance.getWeather(45.75,4.85)):  ViewModel() {

    init {
        viewModelScope.launch {
            WeatherRepository.instance.refreshWeather(45.75,4.85)
        }
    }

}