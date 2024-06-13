package com.example.hapi.data.remote.response

data class DetectionResponse(
    val disease_name: String,
    val certainty: Float,
    val link: String,
    val crop: String,
    val username: String,
    val date: String,
    val time: String,
    val image_url: String
)
