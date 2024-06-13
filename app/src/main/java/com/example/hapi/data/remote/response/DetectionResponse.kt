package com.example.hapi.data.remote.response

data class DetectionResponse(
    val diseaseName: String,
    val certainty: Float,
    val link: String,
    val crop: String,
    val username: String,
    val date: String,
    val time: String,
    val image_url: String
)
