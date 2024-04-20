package com.example.hapi.data.remote.response

data class SigninResponse(
    val token: String,
    val role: String,
    val username : String,
    val landId: String,
    val crop: String?
)
