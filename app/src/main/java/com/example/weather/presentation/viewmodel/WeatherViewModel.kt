package com.example.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.WeatherRepositoryImpl
import com.example.weather.domain.models.WeatherModel
import com.example.weather.domain.usecases.GetWeather
import com.example.weather.domain.usecases.GetWeatherImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class WeatherViewModel @Inject constructor(
    private val weatherUseCase: GetWeather,
    private val repo: WeatherRepositoryImpl
) : ViewModel() {

    private val _weatherSelected = MutableLiveData<Event<WeatherModel>>()
    val weatherSelected: LiveData<Event<WeatherModel>>
        get() = _weatherSelected

    val weather = MutableLiveData<List<WeatherModel>>()

    fun sendWeather(item: WeatherModel) {
        _weatherSelected.postValue(Event(item))
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
            weatherUseCase(LAT, LON).collect {
                    weather.postValue(it)
                }
        }
    }

}

class Event<ContentType> (
    val rawValue: ContentType
) {
    fun consume() {
        isConsumed = true
    }

    var isConsumed: Boolean = false
        private set

    val value: ContentType?
        get() {
            return if (!isConsumed) {
                isConsumed = true
                rawValue
            } else {
                null
            }
        }
}


