package com.swensonhe.data.repository.landingpage

import com.swensonhe.common.Outcome
import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.common.model.landingpage.current_city_weather_response
import com.swensonhe.data.model.BaseApiResponse
import com.swensonhe.data.repository.DatabaseDataSource
import com.swensonhe.data.repository.PreferenceDataSource
import com.swensonhe.data.repository.RemoteDataSource
import com.swensonhe.domain.repository.LandingPageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LandingPageRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: DatabaseDataSource,
    private val preferenceDataSource: PreferenceDataSource
) : LandingPageRepository, BaseApiResponse() {


    override suspend fun getCityWeather(
        key: String,
        city_name: String
    ): Flow<Outcome<current_city_weather_response>> {
        return flow {
            emit(Outcome.loading(true))
            val landingModelObj = safeApiCall { remoteDataSource.getCityWeather(key, city_name) }
            emit(landingModelObj)
            emit(Outcome.loading(false))
        }.catch {
            emit(Outcome.failure(it))
            emit(Outcome.loading(false))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCitySearch(
        key: String,
        search_text: String
    ): Flow<Outcome<cities_response>> {
        return flow {
            emit(Outcome.loading(true))
            val landingModelObj = safeApiCall { remoteDataSource.getCitySearch(key, search_text) }
            emit(landingModelObj)
            emit(Outcome.loading(false))
        }.catch {
            emit(Outcome.failure(it))
            emit(Outcome.loading(false))
        }.flowOn(Dispatchers.IO)
    }


}