package com.example.weather

import com.example.weather.presentation.fragment.WeatherFragment
import dagger.Subcomponent

@Subcomponent(modules = [WeatherModule::class])
interface WeatherComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherComponent
    }

    fun inject(fragment: WeatherFragment)
}