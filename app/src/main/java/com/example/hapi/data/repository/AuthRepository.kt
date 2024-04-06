package com.example.hapi.data.repository

import com.example.hapi.data.local.AuthPreference
import com.example.hapi.data.model.SigninErrorInfo
import com.example.hapi.data.model.SignupErrorInfo
import com.example.hapi.data.model.State
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.data.remote.request.SigninRequest
import com.example.hapi.data.remote.response.SigninResponse
import com.example.hapi.data.remote.response.SignupResponse
import com.example.hapi.util.FARMER
import com.example.hapi.util.LANDOWNER
import com.example.hapi.util.handleException
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService,
    private val authPreference: AuthPreference
) {
    suspend fun signupLandowner(
        landownerSignupRequest: LandownerSignupRequest
    ): Flow<State<SignupResponse?>> {
        return flow {
            try {
                emit(State.Loading)
                val response = authApiService.signupLandowner(landownerSignupRequest)

                if (response.isSuccessful) {
                    emit(State.Success(response.body()))
                    authPreference.saveAuthToken(response.body()!!.token)
                    authPreference.saveRole(LANDOWNER)
                    authPreference.saveHaveSelectedCrop(false)
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        SignupErrorInfo::class.java
                    )
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    suspend fun signupFarmer(
        farmerSignupRequest: FarmerSignupRequest
    ): Flow<State<SignupResponse?>> {
        return flow {
            try {
                emit(State.Loading)
                val response = authApiService.signupFarmer(farmerSignupRequest)

                if (response.isSuccessful) {
                    emit(State.Success(response.body()))
                    authPreference.saveAuthToken(response.body()!!.token)
                    authPreference.saveRole(FARMER)
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        SignupErrorInfo::class.java
                    )
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    suspend fun signin(
        signinRequest: SigninRequest
    ): Flow<State<SigninResponse?>> {
        return flow {
            try {
                emit(State.Loading)
                val response = authApiService.signin(signinRequest)

                if (response.isSuccessful) {
                    emit(State.Success(response.body()))
                    authPreference.saveAuthToken(response.body()!!.token)
                    authPreference.saveRole(response.body()!!.role)
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        SigninErrorInfo::class.java
                    )
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }
}