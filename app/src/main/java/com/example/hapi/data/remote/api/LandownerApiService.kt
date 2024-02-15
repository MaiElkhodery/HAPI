package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.response.AuthResponse
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.util.LANDOWNER_SIGNUP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LandownerApiService {
    @POST(LANDOWNER_SIGNUP)
    suspend fun signup(
        @Body landownerSignup: LandownerSignupRequest
    ):Response<AuthResponse>
}