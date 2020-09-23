package com.example.weather

import android.content.Context
import com.example.weather.data.database.DataBaseModule
import com.example.weather.data.network.NetworkModule
import com.example.weather.presenter.activity.MainActivity
import com.example.weather.presenter.fragment.WeatherFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    DataBaseModule::class,
    ViewModelBuilderModule::class,
    WeatherModule::class,
    WeatherFragmentModule::class
])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
    fun inject(weatherApplication: WeatherApplication)
    fun inject(fragment: WeatherFragment)

}
