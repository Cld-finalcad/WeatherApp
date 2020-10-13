package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.data.database.WeatherDao
import com.example.weather.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, "weather.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideWeatherDao(
        database: WeatherDatabase
    ): WeatherDao {
        return database.weatherDao()
    }
}