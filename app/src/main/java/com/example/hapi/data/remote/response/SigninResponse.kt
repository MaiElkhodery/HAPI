package com.example.hapi.data.remote.response

data class SigninResponse(
    val token: String,
    val role: String,
    val username : String,
    val land_id: String,
    val crop: String?
)
