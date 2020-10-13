package com.example.weather.di

import com.example.weather.di.WeatherFragmentSubcomponent
import com.example.weather.presentation.fragment.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [WeatherFragmentSubcomponent::class])
abstract class WeatherFragmentModule {
    @ContributesAndroidInjector
    abstract fun weatherFragmentInjector(): WeatherFragment

}