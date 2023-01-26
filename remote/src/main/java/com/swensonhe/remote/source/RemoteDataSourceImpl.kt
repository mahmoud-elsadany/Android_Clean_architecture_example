package com.swensonhe.remote.source


import com.swensonhe.data.repository.RemoteDataSource
import com.swensonhe.remote.api.LandingPageService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val landingPageService: LandingPageService
    ) : RemoteDataSource {



}
