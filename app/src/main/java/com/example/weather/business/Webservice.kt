package com.example.weather.business

import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {
    @GET("onecall")
    suspend fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appid: String) : Weather

}