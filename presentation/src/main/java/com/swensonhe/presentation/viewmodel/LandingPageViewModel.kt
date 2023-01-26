package com.swensonhe.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.swensonhe.common.ErrorModel
import com.swensonhe.common.Outcome
import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.common.model.landingpage.current_city_weather_response
import com.swensonhe.domain.usecases.landingpage.GetCitySearchTask
import com.swensonhe.domain.usecases.landingpage.GetCityWeatherTask
import com.swensonhe.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class LandingPageViewModel @Inject constructor(
    private val getCitySearchTask: GetCitySearchTask,
    private val getCityWeatherTask: GetCityWeatherTask
) : BaseViewModel() {

    val searched_cities = MutableSharedFlow<cities_response>()
    val current_city_weather = MutableSharedFlow<current_city_weather_response>()


    fun getSearchedCities(weatherApiKey: String, search_text: String) {
        viewModelScope.launch {
            getCitySearchTask.buildUseCase(Pair(weatherApiKey, search_text))
                .catch {
                    exceptionState.emit(it.message ?: "")
                }.flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Outcome.Progress -> {
                            loadingState.emit(it.loading)
                        }
                        is Outcome.Failure -> {
                            exceptionState.emit(it.e.message ?: "")
                        }
                        is Outcome.ApiError -> {
                            apiErrorState.emit(ErrorModel(it.errorCode, it.errorMessage))
                        }
                        is Outcome.Success -> {
                            searched_cities.emit(it.data)
                        }
                    }

                }
        }

    }

    fun getCityWeather(weatherApiKey: String, cityName: String) {
        viewModelScope.launch {
            getCityWeatherTask.buildUseCase(Pair(weatherApiKey, cityName))
                .catch {
                    exceptionState.emit(it.message ?: "")
                }.flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Outcome.Progress -> {
                            loadingState.emit(it.loading)
                        }
                        is Outcome.Failure -> {
                            exceptionState.emit(it.e.message ?: "")
                        }
                        is Outcome.ApiError -> {
                            apiErrorState.emit(ErrorModel(it.errorCode, it.errorMessage))
                        }
                        is Outcome.Success -> {
                            current_city_weather.emit(it.data)
                        }
                    }

                }
        }

    }


}
