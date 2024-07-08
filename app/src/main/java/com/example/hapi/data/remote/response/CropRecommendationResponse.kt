package com.example.hapi.data.remote.response

data class CropRecommendationResponse(
    val apple : Double,
    val wheat : Double,
    val corn : Double,
    val sugarcane : Double,
    val tomato : Double,
    val cotton : Double,
    val potato : Double
)