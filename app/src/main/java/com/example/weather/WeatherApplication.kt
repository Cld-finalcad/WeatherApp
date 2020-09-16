package com.example.weather

import android.app.Application
import com.example.weather.data.database.WeatherDatabase

class WeatherApplication: Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: Application): ApplicationComponent =
        DaggerApplicationComponent.builder()
            .application(app)
            .appModule(DataBaseModule(this), NetworkModule())
            .build()
            .inject(app)



           /* .appModule(DataBaseModule(this), NetworkModule())
            .build()*/
}