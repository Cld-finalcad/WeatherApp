package com.example.weather

import com.example.weather.presentation.fragment.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [DetailsFragmentSubcomponent::class])

abstract class DetailsFragmentModule {
    @ContributesAndroidInjector
    abstract fun detailsFragmentInjector(): DetailsFragment

}