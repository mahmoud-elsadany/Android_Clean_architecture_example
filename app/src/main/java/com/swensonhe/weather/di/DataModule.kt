package com.swensonhe.weather.di


import com.swensonhe.data.repository.landingpage.LandingPageRepositoryImpl
import com.swensonhe.domain.repository.LandingPageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {



    @Binds
    abstract fun bindsLandingPageRepository(
        repoImpl: LandingPageRepositoryImpl
    ): LandingPageRepository



}