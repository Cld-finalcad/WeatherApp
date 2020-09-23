package com.example.weather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.data.database.models.WeatherModelDB

@Database(entities = [WeatherModelDB::class], version = 3, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}