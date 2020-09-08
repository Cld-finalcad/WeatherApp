package com.example.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class WeatherViewModel :  ViewModel() {

    val weather: LiveData<List<String>> = WeatherRepository.instance.getWeather()

    //val test: String = WeatherRepository.instance.getWeather()
}