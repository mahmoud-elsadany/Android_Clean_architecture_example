package com.swensonhe.remote.source


import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.common.model.landingpage.current_city_weather_response
import com.swensonhe.data.repository.RemoteDataSource
import com.swensonhe.remote.api.LandingPageService
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val landingPageService: LandingPageService
) : RemoteDataSource {
    override suspend fun getCityWeather(
        key: String,
        city_name: String
    ): Response<current_city_weather_response> {
        return landingPageService.getCityWeather(key, city_name)
    }

    override suspend fun getCitySearch(
        key: String,
        search_text: String
    ): Response<cities_response> {
        return landingPageService.getCitySearch(key, search_text)
    }


}
