package com.swensonhe.weather.di

import android.content.Context
import com.swensonhe.data.repository.DatabaseDataSource
import com.swensonhe.data.repository.PreferenceDataSource
import com.swensonhe.local.database.room.SwensonheWeatherDB
import com.swensonhe.local.source.PreferenceDataSourcempl
import com.swensonhe.local.source.DatabaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [LocalPersistenceModule.Binders::class])
@InstallIn(SingletonComponent::class)
class LocalPersistenceModule {


    @Module
    @InstallIn(SingletonComponent::class)
    interface Binders {
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: DatabaseDataSourceImpl
        ): DatabaseDataSource

    }

    @Provides
    @Singleton
    //provide application context using hilt's @ApplicationContext
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = SwensonheWeatherDB.getInstance(context)


    @Provides
    @Singleton
    fun providesMlModelsDAO(
        swensonheWeatherDB: SwensonheWeatherDB
    ) = swensonheWeatherDB.getTestDAO()


    @Provides
    @Singleton
    fun providesSharedPrefs(
        @ApplicationContext context: Context
    ): PreferenceDataSource = PreferenceDataSourcempl(context)


}
