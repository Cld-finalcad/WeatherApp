package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {
    @GET("onecall?lat=45.75&lon=4.85&appid=dd06a0028d37813dfa3ac07a81c4e269")
    fun getWeather() : Call<String>
    //suspend fun getWeather(): String
}