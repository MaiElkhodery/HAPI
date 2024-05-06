package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.request.CheckPasswordRequest
import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.data.remote.request.SigninRequest
import com.example.hapi.data.remote.response.SigninResponse
import com.example.hapi.data.remote.response.SignupResponse
import com.example.hapi.util.DELETE_ACCOUNT
import com.example.hapi.util.FARMER_SIGNUP
import com.example.hapi.util.LANDOWNER_SIGNUP
import com.example.hapi.util.LOGOUT
import com.example.hapi.util.PASSWORD_CHECK
import com.example.hapi.util.SIGNIN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthApiService {
    @POST(FARMER_SIGNUP)
    suspend fun signupFarmer(
        @Body farmerSignupRequest: FarmerSignupRequest
    ): Response<SignupResponse>

    @POST(LANDOWNER_SIGNUP)
    suspend fun signupLandowner(
        @Body landownerSignup: LandownerSignupRequest
    ): Response<SignupResponse>

    @POST(SIGNIN)
    suspend fun signin(
        @Body signinRequest: SigninRequest
    ): Response<SigninResponse>

    @POST(LOGOUT)
    suspend fun logout(): Response<Unit>

    @DELETE(DELETE_ACCOUNT)
    suspend fun deleteAccount(
        @Body checkPasswordRequest: CheckPasswordRequest
    ): Response<Unit>

    @POST(PASSWORD_CHECK)
    suspend fun checkPassword(
        @Body checkPasswordRequest: CheckPasswordRequest
    ): Response<Unit>
}