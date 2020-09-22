package com.example.weather.data.network

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.weather.data.database.WeatherDao
import com.example.weather.data.database.WeatherDatabase
import com.example.weather.data.models.Daily
import com.example.weather.data.models.Weather
import com.example.weather.domain.models.WeatherModel
import com.example.weather.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val webservice: Webservice,
    private val weatherDao: WeatherDao
)/*(
    val weatherDao: WeatherDao = WeatherDatabase.INSTANCE!!.weatherDao()
)*/ : WeatherRepository {

    companion object {

        val FRESH_TIMEOUT: Long = TimeUnit.DAYS.toMillis(1)

    }

    override fun getWeather(lat: Double, lon: Double): LiveData<List<WeatherModel>> {
        return weatherDao.load(lat, lon)
            .map { weather ->
                Log.d("Repository Weather", weather.toString())
                weather?.daily
                    ?.map {
                        it.toWeatherModel(weather.timezone)
                    }
                    ?: emptyList()
            }
    }

    override suspend fun refreshWeather(lat: Double, lon: Double) = withContext(Dispatchers.IO) {
        val weatherExists = weatherDao.hasWeather(lat, lon) > 0

        if (!weatherExists) {
            val dataset = webservice.getWeather(lat, lon, "dd06a0028d37813dfa3ac07a81c4e269")
            Log.d("Repository", weatherExists.toString() + " " + dataset)
            weatherDao.save(dataset)
        }
    }

    fun Daily.toWeatherModel(timezone: String): WeatherModel {
        return WeatherModel(
            timezone = timezone,
            date = dt,
            main = weather[0].main,
            iconURL = weather[0].icon,
            temperature = temp.day,
            pressure = pressure,
            humidity = humidity,
            wind = wind_speed
        )
    }

}