package com.example.hapi.data.remote.response

import com.example.hapi.domain.model.DetectionHistory

data class DetectionResponse(
    val detection: DetectionHistory,
    val crop: String
)
