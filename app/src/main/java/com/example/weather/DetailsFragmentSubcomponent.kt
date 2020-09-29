package com.example.weather

import com.example.weather.presenter.fragment.DetailsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface DetailsFragmentSubcomponent: AndroidInjector<DetailsFragment> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<DetailsFragment>{}
}