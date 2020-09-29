package com.example.weather

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.weather.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("WeatherSharedPrefs", Context.MODE_PRIVATE)
    }
}