package com.example.weather

import android.content.Context
import com.example.weather.data.models.Weather
import com.example.weather.data.network.WeatherRepositoryImpl
import com.example.weather.presenter.fragment.WeatherFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    DataBaseModule::class,
    ViewModelBuilderModule::class,
    WeatherModule::class/*,
    SubcomponentsModule::class*/
])
interface ApplicationComponent {
    fun inject(fragment: WeatherFragment)

    //fun weatherComponent(): WeatherComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}

/*
@Module(
    subcomponents = [
    WeatherComponent::class
    ]
)
object SubcomponentsModule*/
