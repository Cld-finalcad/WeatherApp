package com.example.weather.presenter.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.weather.data.network.WeatherRepositoryImpl
import com.example.weather.domain.models.WeatherModel
import com.example.weather.domain.repositories.WeatherRepository
import com.example.weather.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class WeatherViewModel @Inject constructor(
    private val weatherUseCase: GetWeatherUseCase,
    private val repo: WeatherRepositoryImpl
) : ViewModel() {

    companion object {
        const val LAT = 45.75
        const val LON = 4.85

    }


    init {

        viewModelScope.launch {
            repo.refreshWeather(LAT, LON)
        }
    }


    val weather: LiveData<List<WeatherModel>> = weatherUseCase.getWeather(LAT, LON)


}