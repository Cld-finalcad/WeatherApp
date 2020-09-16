package com.example.data.repo

import com.example.data.mappers.WeatherMappers
import com.example.data.webservice.Webservice
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RepoImpl (
    private val webservice: Webservice = retrofit.create(
        Webservice::class.java
    ), private val weatherMappers : WeatherMappers = WeatherMappers()
): Repository {

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val FRESH_TIMEOUT: Long = TimeUnit.DAYS.toMillis(1)
        val instance: RepoImpl by lazy { RepoImpl() }

    }

    fun getWeather(lat: Double, lon: Double): LiveData<Weather> {
        return weatherDao.load(lat, lon)
    }


}