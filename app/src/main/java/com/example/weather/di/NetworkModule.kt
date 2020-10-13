package com.example.weather.di

import com.example.weather.data.WeatherRepositoryImpl
import com.example.weather.data.network.Webservice
import com.example.weather.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideWebService(): Webservice {
        return (Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(Webservice::class.java))
    }

    @Provides
    fun providesWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository {
        return weatherRepositoryImpl
    }

}
