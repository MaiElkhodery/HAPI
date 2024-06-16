package com.example.hapi.data.remote.response

data class DiseaseDetectionResponse(
    val disease_name: String,
    val certainty: Float,
    val info_link: String
)
