package com.example.weather.business

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(weather: Weather)

    @Query("SELECT * FROM weather WHERE lat = :latitude AND lon = :longitude")
    fun load(latitude: Double, longitude: Double): LiveData<Weather>

    @Query("SELECT COUNT(*) FROM weather WHERE lat == :lat AND lon = :lon")
    fun  hasWeather(lat: Double, lon: Double): Int

    @Query("DELETE FROM weather")
    fun nukeTable()
}