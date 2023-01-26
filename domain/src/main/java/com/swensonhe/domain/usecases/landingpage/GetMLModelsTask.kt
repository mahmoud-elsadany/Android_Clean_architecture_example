package com.swensonhe.domain.usecases.landingpage



class GetMLModelsTask


//@Inject constructor(
//    private val landingPageRepository: LandingPageRepository,
//    defaultDispatcher: CoroutineDispatcher
//) : FlowableUseCase<landingModelResponse, String>(
//    defaultDispatcher
//) {
//
//    override suspend fun generateFlowable(
//        input: String?,
//        networStatus: Boolean
//    ): Flow<Outcome<landingModelResponse>> {
//        if (input == null) {
//            throw IllegalArgumentException("Post object cann't be null ")
//        }
//        return landingPageRepository.getMLModels(networStatus, input)
//    }
//
//}