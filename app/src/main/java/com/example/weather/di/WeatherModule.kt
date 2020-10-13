package com.example.weather.di

import androidx.lifecycle.ViewModel
import com.example.weather.di.ViewModelKey
import com.example.weather.domain.usecases.GetWeather
import com.example.weather.domain.usecases.GetWeatherImpl
import com.example.weather.presentation.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeatherModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindViewModel(viewModel: WeatherViewModel): ViewModel

    @Binds
    abstract fun provideGetWeather(impl: GetWeatherImpl): GetWeather
}