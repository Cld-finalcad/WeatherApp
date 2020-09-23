package com.example.weather.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weather.data.database.models.WeatherModelDB
import com.example.weather.data.models.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(weather: List<WeatherModelDB>)

    @Query("SELECT * FROM weathermodeldb WHERE lat = :latitude AND lon = :longitude")
    fun load(latitude: Double, longitude: Double): LiveData<List<WeatherModelDB>?>

    @Query("SELECT * FROM weathermodeldb")
    fun loadAll(): List<WeatherModelDB>

    @Query("SELECT COUNT(*) FROM weathermodeldb WHERE lat == :lat AND lon = :lon")
    fun  hasWeather(lat: Double, lon: Double): Int

    @Query("DELETE FROM weathermodeldb")
    fun nukeTable()
}