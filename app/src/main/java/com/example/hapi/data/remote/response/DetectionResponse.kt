package com.example.hapi.data.remote.response

import com.example.hapi.domain.model.Disease

data class DetectionResponse(
    val diseases: List<Disease>,
    val isHealthy: Boolean,
    val confidence: Float
)
