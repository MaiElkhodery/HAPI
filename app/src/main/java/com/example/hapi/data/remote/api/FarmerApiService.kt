package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.response.AuthResponse
import com.example.hapi.util.FARMER_SIGNUP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FarmerApiService {
    @POST(FARMER_SIGNUP)
    suspend fun signup(
        @Body farmerSignupRequest: FarmerSignupRequest
    ):Response<AuthResponse>
}