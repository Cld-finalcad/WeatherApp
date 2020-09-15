package com.example.weather.ui

import android.app.Application
import com.example.weather.business.database.WeatherDatabase

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        WeatherDatabase.getInstance(this)
    }
}