package com.example.weather

import com.example.weather.presenter.fragment.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [WeatherFragmentSubcomponent::class])
abstract class WeatherFragmentModule {
    @ContributesAndroidInjector
    abstract fun weatherFragmentInjector(): WeatherFragment

}