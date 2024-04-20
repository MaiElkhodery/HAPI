package com.example.hapi.data.remote.response

import com.example.hapi.domain.model.DetectionHistory

data class DetectionResponse(
    val detection: DetectionHistory,
    val crop: String,
    val username: String,
    val date: String,
    val time: String,
    val image_url:String
)
