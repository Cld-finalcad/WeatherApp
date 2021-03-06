package com.example.weather.domain.usecases

import com.example.weather.domain.models.WeatherModel
import com.example.weather.domain.models.WeatherModelRaw
import com.example.weather.domain.repositories.WeatherRepository
import com.example.weather.domain.utils.Convertor
import com.example.weather.domain.utils.Recommandations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

interface GetWeather {
    operator fun invoke(lat: Double, lon: Double): Flow<List<WeatherModel>>
}

class GetWeatherImpl @Inject constructor(
    private val repo: WeatherRepository
) : GetWeather {
    override fun invoke(lat: Double, lon: Double): Flow<List<WeatherModel>> {
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
            date = Convertor.getDate(this.date, Locale.getDefault().country),
            main = main,
            iconURL = iconURL,
            temperature = Convertor.getTemp(temperature, Locale.getDefault().country),
            pressure = pressure,
            humidity = humidity,
            wind = wind,
            flags = Recommandations.setFlags(main, temperature, wind)
        )
    }
}