package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.remote.ApiHandler
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.data.remote.request.PasswordRequest
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
    private val userDataPreference: UserDataPreference,
    private val apiHandler: ApiHandler
) {
    suspend fun signupLandowner(
        landownerSignupRequest: LandownerSignupRequest
    ): Flow<State<SignupResponse?>> {

        return apiHandler.makeRequest(
            execute = { authApiService.signupLandowner(landownerSignupRequest) },
            onSuccess = { response ->
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
        return apiHandler.makeRequest(
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
        return apiHandler.makeRequest(
            execute = {
                authApiService.signin(signinRequest)
            },
            onSuccess = { response ->
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
        return apiHandler.makeRequest(
            execute = { authApiService.logout() },
            onSuccess = {
                clearUserDataPreference()
            }
        )
    }

    suspend fun deleteAccount(): Flow<State<Unit>> {
        return apiHandler.makeRequest(
            execute = {
                authApiService.deleteAccount()
            },
            onSuccess = {
                clearUserDataPreference()
            }
        )
    }

    suspend fun checkPassword(password: String): Flow<State<Unit>> {
        return apiHandler.makeRequest(
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
        userDataPreference.saveNitrogenTankLevel("0")
        userDataPreference.savePhosphorusTankLevel("0")
        userDataPreference.savePotassiumTankLevel("0")
        userDataPreference.saveWaterLevel(0)
        userDataPreference.saveCrop("")
        userDataPreference.saveAuthToken("")
        userDataPreference.saveLastDetectionHistoryId("1")
        userDataPreference.saveLastLandDataHistoryId("1")
        userDataPreference.saveRole("")
    }
}