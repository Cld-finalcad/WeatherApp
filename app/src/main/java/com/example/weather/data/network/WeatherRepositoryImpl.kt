package com.example.weather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.weather.data.database.WeatherDao
import com.example.weather.data.database.models.WeatherModelDB
import com.example.weather.data.models.Daily
import com.example.weather.domain.models.WeatherModel
import com.example.weather.domain.repositories.WeatherRepository
import com.example.weather.domain.utils.Convertor
import com.example.weather.domain.utils.Recommandations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val webservice: Webservice,
    private val weatherDao: WeatherDao
) : WeatherRepository {

    companion object {

        val FRESH_TIMEOUT: Long = TimeUnit.DAYS.toMillis(1)

    }

    override fun getWeather(lat: Double, lon: Double): LiveData<List<WeatherModel>> {
        return weatherDao.load(lat, lon)
            .map { weather ->
                Log.d("Repository get map", weather.toString())
                weather?.map {
                     it.toWeatherModel()
                    }
                    ?: emptyList()
            }
    }

    override suspend fun refreshWeather(lat: Double, lon: Double) = withContext(Dispatchers.IO) {
        val weatherExists = weatherDao.hasWeather(lat, lon) > 0

        if (!weatherExists) {
            val dataset = webservice.getWeather(lat, lon, "dd06a0028d37813dfa3ac07a81c4e269")
            Log.d("Repository refresh", weatherExists.toString() + " " + dataset)
            val timestamp = System.currentTimeMillis() / 1000
            val weatherModels = dataset.daily.map {
                it.toWeatherModelDB(dataset.timezone, timestamp, lon, lat)
            }
            weatherDao.save(weatherModels)
        }
    }

    fun Daily.toWeatherModelDB(timezone: String, timestamp: Long, lon: Double, lat: Double): WeatherModelDB {
        return WeatherModelDB(
            lat = lat,
            lon = lon,
            timestamp = timestamp,
            timezone = timezone,
            date = dt.toString(),
            main = weather[0].main,
            iconURL = weather[0].icon,
            temperature = temp.day,
            pressure = pressure,
            humidity = humidity,
            wind = wind_speed
        )
    }

    fun WeatherModelDB.toWeatherModel(): WeatherModel {
        return WeatherModel(
            timezone = timezone,
            date = Convertor.getDate(this.date),
            main = main,
            iconURL = iconURL,
            temperature = Convertor.getTemp(temperature),
            pressure = pressure,
            humidity = humidity,
            wind = wind,
            flags = Recommandations.setFlags(main, temperature, wind)
        )
    }

}