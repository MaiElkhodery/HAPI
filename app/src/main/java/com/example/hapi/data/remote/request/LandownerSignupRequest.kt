package com.example.hapi.data.remote.request

data class LandownerSignupRequest(
    val role: String,
    val username: String,
    val password: String,
    val phone_number: String
)
