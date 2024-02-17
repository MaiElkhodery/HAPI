package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.AuthPreference
import com.example.hapi.data.model.ErrorBody
import com.example.hapi.data.model.ErrorInfo
import com.example.hapi.data.model.State
import com.example.hapi.data.remote.api.FarmerApiService
import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.data.remote.response.AuthResponse
import com.example.hapi.util.handleException
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FarmerRepository @Inject constructor(
    private val farmerApiService: FarmerApiService,
    private val authPreference: AuthPreference
) {
    suspend fun signup(
        farmerSignupRequest: FarmerSignupRequest
    ): Flow<State<AuthResponse?>> {
        return flow {
            try {
                emit(State.Loading)
                val response = farmerApiService.signup(farmerSignupRequest)

                if (response.isSuccessful) {
                    emit(State.Success(response.body()))
                    authPreference.saveAuthToken(response.body()!!.token)
                    Log.d("TOKEN", response.body()!!.token)
                } else {
                    Log.d("ERROR", response.errorBody().toString())
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        ErrorInfo::class.java
                    )
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                Log.d("SIGNUP", e.message.toString())
                handleException(e)
            }
        }
    }
}