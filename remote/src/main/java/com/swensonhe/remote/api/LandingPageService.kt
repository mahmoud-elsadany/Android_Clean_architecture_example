package com.swensonhe.remote.api

import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.common.model.landingpage.current_city_weather_response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface LandingPageService {

    @GET("current.json")
    suspend fun getCityWeather(
        @Query("key") key:String,
        @Query("q") city_name:String
    ): Response<current_city_weather_response>

    @GET("search.json")
    suspend fun getCitySearch(
        @Query("key") key:String,
        @Query("q") search_text:String
    ): Response<cities_response>



}