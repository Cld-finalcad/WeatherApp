package com.example.weather

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
/*
@Module
@InstallIn(ActivityComponent::class)*/
abstract class WeatherModule {

   /* @Binds
    abstract fun bindWebService (
        repository: WeatherRepository
    ): Webservice

    @Provides
    fun provideWebService(
    ): Webservice {
        return Retrofit.Builder()
            .baseUrl("")
            .build()
            .create(Webservice::class.java)
    }*/
}
