package com.swensonhe.domain.usecases.landingpage

import com.swensonhe.common.Outcome
import com.swensonhe.common.model.landingpage.cities_response
import com.swensonhe.domain.repository.LandingPageRepository
import com.swensonhe.domain.usecases.base.FlowableUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitySearchTask @Inject constructor(
    private val landingPageRepository: LandingPageRepository,
    defaultDispatcher: CoroutineDispatcher
) : FlowableUseCase<cities_response, Pair<String,String>>(
    defaultDispatcher
) {

    override suspend fun generateFlowable(
        input: Pair<String,String>?,
        networStatus: Boolean
    ): Flow<Outcome<cities_response>> {
        if (input == null) {
            throw IllegalArgumentException("Post object cann't be null ")
        }
        return landingPageRepository.getCitySearch(key = input.first, search_text = input.second)
    }

}