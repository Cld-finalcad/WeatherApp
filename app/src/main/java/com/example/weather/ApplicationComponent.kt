package com.example.weather

import android.content.Context
import com.example.weather.data.database.DataBaseModule
import com.example.weather.data.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    DataBaseModule::class,
    SharedPreferencesModule::class,
    ViewModelBuilderModule::class,
    WeatherModule::class,
    WeatherFragmentModule::class,
    DetailsFragmentModule::class
])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
    fun inject(weatherApplication: WeatherApplication)
}
