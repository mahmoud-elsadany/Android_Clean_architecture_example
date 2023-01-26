package com.swensonhe.weather.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swensonhe.presentation.base.BaseViewModel
import com.swensonhe.presentation.factory.ViewModelFactory
import com.swensonhe.presentation.viewmodel.LandingPageViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap


@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(LandingPageViewModel::class)
    abstract fun bindsLandingPageViewModel(landingPageVM: LandingPageViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    abstract fun bindsBaseViewModel(baseVM: BaseViewModel): ViewModel





}