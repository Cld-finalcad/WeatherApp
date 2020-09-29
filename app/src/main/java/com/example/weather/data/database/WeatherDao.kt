package com.example.weather.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weather.data.database.models.WeatherModelDB

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(weather: List<WeatherModelDB>)

    @Query("SELECT * FROM week_weather WHERE lat = :latitude AND lon = :longitude")
    fun load(latitude: Double, longitude: Double): LiveData<List<WeatherModelDB>?>

    @Query("SELECT * FROM week_weather")
    fun loadAll(): List<WeatherModelDB>

    @Query("SELECT COUNT(*) FROM week_weather WHERE lat == :lat AND lon = :lon")
    fun  hasWeather(lat: Double, lon: Double): Int

    @Query("DELETE FROM week_weather")
    fun nukeTable()
}