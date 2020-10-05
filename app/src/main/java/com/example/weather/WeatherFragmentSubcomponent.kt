package com.example.weather

import com.example.weather.presentation.fragment.WeatherFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface WeatherFragmentSubcomponent: AndroidInjector<WeatherFragment> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<WeatherFragment>{}
}