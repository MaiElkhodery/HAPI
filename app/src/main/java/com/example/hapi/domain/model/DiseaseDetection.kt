package com.example.hapi.domain.model

data class DiseaseDetection (
    val id: Int? = null,
    val username: String,
    val date: String,
    val time: String,
    val certainty: Float,
    val diseaseName : String,
    val link : String,
    val crop: String,
    val image: ByteArray,
)