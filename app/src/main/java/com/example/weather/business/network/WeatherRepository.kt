package com.example.weather.business.network

import androidx.lifecycle.LiveData
import com.example.weather.business.database.WeatherDao
import com.example.weather.business.database.WeatherDatabase
import com.example.weather.business.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class WeatherRepository(
    private val webservice: Webservice = retrofit.create(
        Webservice::class.java
    ),
    private val weatherDao: WeatherDao = WeatherDatabase.INSTANCE!!.weatherDao()
) {

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val FRESH_TIMEOUT: Long = TimeUnit.DAYS.toMillis(1)
        val instance: WeatherRepository by lazy { WeatherRepository() }

    }

    fun getWeather(lat: Double, lon: Double): LiveData<Weather> {
        return weatherDao.load(lat, lon)
    }

    suspend fun refreshWeather(lat: Double, lon: Double) = withContext(Dispatchers.IO) {

        val weatherExists = weatherDao.hasWeather(lat, lon) > 0

        if (!weatherExists) {
            val dataset = webservice.getWeather(lat, lon, "dd06a0028d37813dfa3ac07a81c4e269")
            weatherDao.save(dataset)


        }
    }

}