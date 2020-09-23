package com.example.weather

import com.example.weather.presenter.fragment.WeatherFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface WeatherFragmentSubcomponent: AndroidInjector<WeatherFragment> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<WeatherFragment>{}

}