package com.example.hapi.domain.model

data class DetectionHistory(
    val diseases: List<Disease>,
    val confidence: Float,
    val isHealthy: Boolean,
)
