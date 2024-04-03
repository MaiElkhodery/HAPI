package com.example.hapi.data.model

data class Detection (
    val id: Int? = null,
    val username: String,
    val crop: String,
    val imagePath: String,
    val date: String,
    val time: String,
    val possibleDiseases: List<Disease>?,
    val isHealthy: Boolean = false,
    val confidence: Float = 0.0f
)