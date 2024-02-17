package com.example.hapi.data.remote.request

data class FarmerSignupRequest(
    val role: String,
    val username: String,
    val password: String,
    val phone_number: String,
    val land_id:String
)
