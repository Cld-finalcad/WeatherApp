package com.example.weather

import android.app.Application

class WeatherApplication: Application() {
    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)
}