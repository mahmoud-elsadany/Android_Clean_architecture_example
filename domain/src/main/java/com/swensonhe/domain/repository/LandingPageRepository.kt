package com.swensonhe.domain.repository

import com.swensonhe.common.Outcome
import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.common.model.landingpage.current_city_weather_response
import kotlinx.coroutines.flow.Flow

interface LandingPageRepository {


    suspend fun getCityWeather(
        key:String,
        city_name:String
    ): Flow<Outcome<current_city_weather_response>>

    suspend fun getCitySearch(
        key:String,
        search_text:String
    ): Flow<Outcome<cities_response>>






}