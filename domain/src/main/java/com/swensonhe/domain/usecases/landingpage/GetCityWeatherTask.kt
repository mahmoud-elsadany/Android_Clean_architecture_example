package com.swensonhe.domain.usecases.landingpage

import com.swensonhe.common.Outcome
import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.common.model.landingpage.current_city_weather_response
import com.swensonhe.domain.repository.LandingPageRepository
import com.swensonhe.domain.usecases.base.FlowableUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityWeatherTask @Inject constructor(
    private val landingPageRepository: LandingPageRepository,
    defaultDispatcher: CoroutineDispatcher
) : FlowableUseCase<current_city_weather_response, Pair<String, String>>(
    defaultDispatcher
) {

    override suspend fun generateFlowable(
        input: Pair<String, String>?,
        networStatus: Boolean
    ): Flow<Outcome<current_city_weather_response>> {
        if (input == null) {
            throw IllegalArgumentException("Post object cann't be null ")
        }
        return landingPageRepository.getCityWeather(key = input.first, city_name = input.second)
    }

}