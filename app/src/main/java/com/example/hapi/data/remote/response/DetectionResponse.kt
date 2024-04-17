package com.example.hapi.data.remote.response

import com.example.hapi.domain.model.DetectionHistory

data class DetectionResponse(
    val id : Int,
    val username: String,
    val image_url: String,
    val detection: DetectionHistory,
    val time: String,
    val date: String,
    val crop: String
)
