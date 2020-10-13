package com.example.weather.di

import com.example.weather.di.DetailsFragmentSubcomponent
import com.example.weather.presentation.fragment.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [DetailsFragmentSubcomponent::class])

abstract class DetailsFragmentModule {
    @ContributesAndroidInjector
    abstract fun detailsFragmentInjector(): DetailsFragment

}