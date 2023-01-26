package com.swensonhe.data.repository

import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.common.model.landingpage.current_city_weather_response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteDataSource {

    suspend fun getCityWeather(
        key:String,
        city_name:String
    ): Response<current_city_weather_response>

    suspend fun getCitySearch(
        key:String,
        search_text:String
    ): Response<cities_response>


}