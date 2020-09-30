package com.example.weather.domain.usecases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.weather.data.WeatherRepositoryImpl
import com.example.weather.domain.models.WeatherModel
import com.example.weather.domain.models.WeatherModelRaw
import com.example.weather.domain.utils.Convertor
import com.example.weather.domain.utils.Recommandations
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repo: WeatherRepositoryImpl) {
    fun getWeather(lat: Double, lon: Double): LiveData<List<WeatherModel>> {
        return repo.getWeather(lat, lon).map { weather ->
            weather?.map {
                it.toWeatherModel()
            }
                ?: emptyList()
        }
    }

    fun WeatherModelRaw.toWeatherModel(): WeatherModel {
        return WeatherModel(
            timezone = timezone,
            date = Convertor.getDate(this.date),
            main = main,
            iconURL = iconURL,
            temperature = Convertor.getTemp(temperature),
            pressure = pressure,
            humidity = humidity,
            wind = wind,
            flags = Recommandations.setFlags(main, temperature, wind).toTypedArray()
        )
    }
}