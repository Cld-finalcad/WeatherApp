package com.example.weather

import androidx.lifecycle.ViewModel
import com.example.weather.presenter.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeatherModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindViewModel(viewModel: WeatherViewModel): ViewModel
}