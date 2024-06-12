package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.request.PasswordRequest
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
                Log.d("AuthRepository", "signupLandowner: $response")
                userDataPreference.saveAuthToken(response.token)
                userDataPreference.saveRole(LANDOWNER)
                userDataPreference.saveUsername(response.username)
                userDataPreference.saveLandId(response.land_id)
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
                userDataPreference.saveLandId(response.land_id)
            }
        )
    }

    suspend fun signin(
        signinRequest: SigninRequest
    ): Flow<State<SigninResponse?>> {
        return ApiHandler().makeRequest(
            execute = {
                authApiService.signin(signinRequest).apply {
                    Log.d("AuthRepository", "signin: $this")

                }
            },
            onSuccess = { response ->
                Log.d("AuthRepository", "signin: $response")
                userDataPreference.saveAuthToken(response.token)
                userDataPreference.saveRole(response.role)
                userDataPreference.saveUsername(response.username)
                userDataPreference.saveLandId(response.land_id)
                if (response.crop != null) {
                    userDataPreference.saveCrop(response.crop)
                }
            }
        )
    }

    suspend fun logout(): Flow<State<Unit>> {
        return ApiHandler().makeRequest(
            execute = { authApiService.logout() },
            onSuccess = {
                clearUserDataPreference()
            }
        )
    }

    suspend fun deleteAccount(password: String): Flow<State<Unit>> {
        return ApiHandler().makeRequest(
            execute = {
                authApiService.deleteAccount(
                    PasswordRequest(password)
                ).apply {
                    Log.d("AuthRepository", "deleteAccount: $this")
                }
            },
            onSuccess = {
                clearUserDataPreference()
            }
        )
    }

    suspend fun checkPassword(password:String): Flow<State<Unit>> {
        return ApiHandler().makeRequest(
            execute = {
                authApiService.checkPassword(
                    PasswordRequest(password)
                )
            }
        )
    }

    private suspend fun clearUserDataPreference() {
        userDataPreference.saveUsername("")
        userDataPreference.saveLandId("")
        userDataPreference.saveNitrogenTankLevel("")
        userDataPreference.savePhosphorusTankLevel("")
        userDataPreference.savePotassiumTankLevel("")
        userDataPreference.saveCrop("")
        userDataPreference.saveAuthToken("")
        userDataPreference.saveLastDetectionHistoryId("1")
        userDataPreference.saveLastLandDataHistoryId("1")
        userDataPreference.saveRole("")
    }
}