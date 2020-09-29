package com.example.weather.data

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weather.data.database.WeatherDao
import com.example.weather.data.database.models.WeatherModelDB
import com.example.weather.data.models.Daily
import com.example.weather.data.network.Webservice
import com.example.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val webservice: Webservice,
    private val weatherDao: WeatherDao,
    private val sharedPreferences: SharedPreferences
) : WeatherRepository {

    companion object {
        val FRESH_TIMEOUT: Long = TimeUnit.DAYS.toMillis(1)
    }

    override fun getWeather(lat: Double, lon: Double): LiveData<List<WeatherModelDB>?> {
        return weatherDao.load(lat, lon)
    }

    override suspend fun refreshWeather(lat: Double, lon: Double) = withContext(Dispatchers.IO) {
        val weatherExists = weatherDao.hasWeather(lat, lon) > 0

        if (!weatherExists or (getTimeStamp() >= FRESH_TIMEOUT)) {
            val dataset = webservice.getWeather(lat, lon, "dd06a0028d37813dfa3ac07a81c4e269")
            Log.d("Repository refresh", weatherExists.toString() + " " + dataset)
            setTimeStamp()

            val weatherModels = dataset.daily.map {
                it.toWeatherModelDB(dataset.timezone, lon, lat)
            }
            weatherDao.save(weatherModels)
        }
    }


    fun setTimeStamp() {
        val timestamp = System.currentTimeMillis() / 1000
        val editor = sharedPreferences.edit()
        editor.putLong("timestamp", timestamp)
        editor.apply()
    }

    fun getTimeStamp(): Long {
        val timestamp = System.currentTimeMillis() / 1000

        return timestamp - sharedPreferences.getLong("timestamp", 0)
    }

    fun Daily.toWeatherModelDB(timezone: String, lon: Double, lat: Double): WeatherModelDB {
        return WeatherModelDB(
            lat = lat,
            lon = lon,
            timezone = timezone,
            date = Date(dt.toLong()),
            main = weather[0].main,
            iconURL = weather[0].icon,
            temperature = temp.day,
            pressure = pressure,
            humidity = humidity,
            wind = wind_speed
        )
    }
}