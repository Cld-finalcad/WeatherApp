package com.example.weather.di

import com.example.weather.presentation.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity
}