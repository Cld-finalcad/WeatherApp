package com.example.weather

import com.example.weather.data.database.WeatherDao
import com.example.weather.data.network.WeatherRepositoryImpl
import com.example.weather.presenter.activity.MainActivity
import com.example.weather.presenter.fragment.WeatherFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataBaseModule::class])
interface ApplicationComponent {
    fun inject (activity: MainActivity)
    fun inject (fragment: WeatherFragment)
    fun inject (repositoryImpl: WeatherRepositoryImpl)
    fun inject (weatherDao: WeatherDao)
}