package com.example.weather.ui

import android.app.Application
import com.example.weather.business.WeatherDatabase

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        WeatherDatabase.getInstance(this)
    }
}