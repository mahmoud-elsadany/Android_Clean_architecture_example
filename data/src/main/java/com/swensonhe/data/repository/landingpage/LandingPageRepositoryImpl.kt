package com.swensonhe.data.repository.landingpage

import com.swensonhe.data.model.BaseApiResponse
import com.swensonhe.data.repository.DatabaseDataSource
import com.swensonhe.data.repository.PreferenceDataSource
import com.swensonhe.data.repository.RemoteDataSource
import com.swensonhe.domain.repository.LandingPageRepository
import javax.inject.Inject

class LandingPageRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: DatabaseDataSource,
    private val preferenceDataSource: PreferenceDataSource
) : LandingPageRepository, BaseApiResponse() {


//    override suspend fun getMLModels(
//        networkStatus: Boolean,
//        appName: String
//    ): Flow<Outcome<landingModelResponse>> {
//        return flow {
//            emit(Outcome.loading(true))
//            val landingModelObj = safeApiCall { remoteDataSource.getMLModels(appName) }
//            emit(landingModelObj)
//            emit(Outcome.loading(false))
//        }.catch {
//            emit(Outcome.failure(it))
//            emit(Outcome.loading(false))
//        }.flowOn(Dispatchers.IO)
//
//
//    }


//    override suspend fun <T> saveLandingPageResponse(key: Preferences.Key<T>, value: T) {
//        preferenceDataSource.putPreference(key, value)
//    }
//
//    override suspend fun <T> getSavedLandingPageResponse(
//        key: Preferences.Key<T>,
//        defaultValue: T
//    ): Flow<T> {
//        return preferenceDataSource.getPreference(key, defaultValue)
//    }


}