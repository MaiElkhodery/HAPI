package com.example.hapi.data.model

data class Detection (
    val id: Int? = null,
    val username: String,
    val crop: String,
    val image_path: String,
    val date: String,
    val time: String,
    val possible_diseases: List<Disease>
)