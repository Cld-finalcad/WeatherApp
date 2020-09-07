package com.example.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {

    val repository = WeatherRepository()

    val weather: LiveData<List<String>> = repository.getWeather()
}