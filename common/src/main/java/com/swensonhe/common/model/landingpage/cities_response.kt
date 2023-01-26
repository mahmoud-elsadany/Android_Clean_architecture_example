package com.swensonhe.common.model.landingpage

import androidx.annotation.Keep

@Keep
class cities_response : ArrayList<cities_responseItem>()

@Keep
data class cities_responseItem(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)