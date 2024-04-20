package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.data.remote.request.SigninRequest
import com.example.hapi.data.remote.response.SigninResponse
import com.example.hapi.data.remote.response.SignupResponse
import com.example.hapi.domain.model.State
import com.example.hapi.util.FARMER
import com.example.hapi.util.LANDOWNER
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService,
    private val userDataPreference: UserDataPreference
) : ApiHandler() {
    suspend fun signupLandowner(
        landownerSignupRequest: LandownerSignupRequest
    ): Flow<State<SignupResponse?>> {

        return ApiHandler().makeRequest(
            execute = { authApiService.signupLandowner(landownerSignupRequest) },
            onSuccess = { response ->
                userDataPreference.saveAuthToken(response.token)
                userDataPreference.saveRole(LANDOWNER)
                userDataPreference.saveUsername(response.username)
                userDataPreference.saveLandId(response.landId)
            }
        )
    }

    suspend fun signupFarmer(
        farmerSignupRequest: FarmerSignupRequest
    ): Flow<State<SignupResponse?>> {
        return ApiHandler().makeRequest(
            execute = { authApiService.signupFarmer(farmerSignupRequest) },
            onSuccess = { response ->
                userDataPreference.saveAuthToken(response.token)
                userDataPreference.saveRole(FARMER)
                userDataPreference.saveUsername(response.username)
                userDataPreference.saveLandId(response.landId)
            }
        )
    }

    suspend fun signin(
        signinRequest: SigninRequest
    ): Flow<State<SigninResponse?>> {
        return ApiHandler().makeRequest(
            execute = { authApiService.signin(signinRequest) },
            onSuccess = { response ->
                userDataPreference.saveAuthToken(response.token)
                userDataPreference.saveRole(response.role)
                userDataPreference.saveUsername(response.username)
                userDataPreference.saveLandId(response.landId)
                if (response.crop != null) {
                    userDataPreference.saveCrop(response.crop)
                }
            }
        )
    }
}