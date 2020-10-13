package com.example.weather.di

import com.example.weather.presentation.fragment.DetailsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface DetailsFragmentSubcomponent: AndroidInjector<DetailsFragment> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<DetailsFragment>{}
}