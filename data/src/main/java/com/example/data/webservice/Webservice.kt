package com.example.data.webservice

import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {
    @GET("onecall")
    suspend fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appid: String) : String /*Weather*/

}