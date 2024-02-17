package com.example.hapi.data.model

data class ErrorInfo(
    val message: String,
    val errors: Map<String, List<String>>
)