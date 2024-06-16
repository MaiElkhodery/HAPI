package com.example.hapi.data.remote.response

data class DetectionResponse(
    val username: String,
    val image_url: String,
    val date: String,
    val time: String,
    val disease_name: String,
    val certainty: Float,
    val info_link: String,
    val crop: String
)
