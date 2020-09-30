package com.example.weather.presenter.viewmodel

import androidx.lifecycle.*
import com.example.weather.data.WeatherRepositoryImpl
import com.example.weather.domain.models.WeatherModel
import com.example.weather.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class WeatherViewModel @Inject constructor(
    private val weatherUseCase: GetWeatherUseCase,
    private val repo: WeatherRepositoryImpl
) : ViewModel() {

    private val _weatherSelected = MutableLiveData<WeatherModel>()
    val weatherSelected: LiveData<WeatherModel>
        get() = _weatherSelected

    val weather = MutableLiveData<List<WeatherModel>>()

    fun sendWeather(item: WeatherModel) {
        _weatherSelected.postValue(item)
    }

    companion object {
        const val LAT = 45.75
        const val LON = 4.85

    }


    init {

        viewModelScope.launch {
            repo.refreshWeather(LAT, LON)
        }

        viewModelScope.launch {
            weatherUseCase.getWeather(LAT, LON).collect {
                weather.postValue(it)
            }
        }
    }

}