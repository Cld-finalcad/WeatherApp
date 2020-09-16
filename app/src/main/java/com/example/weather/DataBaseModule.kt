package com.example.weather

import androidx.room.Room
import com.example.weather.data.database.WeatherDao
import com.example.weather.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(application: WeatherApplication) {

    private val context = application
    lateinit var database: WeatherDatabase

    @Singleton
    @Provides
    fun provideWeatherDatabase(): WeatherDatabase {
        database = Room.databaseBuilder(context,
            WeatherDatabase::class.java, "weather.db").build()
        return database
    }

    @Provides
    fun provideWeatherDao(): WeatherDao {
        return  database.weatherDao()
    }

}