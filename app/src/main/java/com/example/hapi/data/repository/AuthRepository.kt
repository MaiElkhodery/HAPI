package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.datastore.AuthPreference
import com.example.hapi.data.local.room.dao.landowner.LandownerDao
import com.example.hapi.data.local.room.entities.landowner.Landowner
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
    private val authPreference: AuthPreference,
    private val landownerDao: LandownerDao
) : ApiHandler() {
    suspend fun signupLandowner(
        landownerSignupRequest: LandownerSignupRequest
    ): Flow<State<SignupResponse?>> {

        return ApiHandler().makeRequest(
            execute = { authApiService.signupLandowner(landownerSignupRequest) },
            onSuccess = { response ->
                authPreference.saveAuthToken(response.token)
                authPreference.saveRole(LANDOWNER)
                landownerDao.insertOrUpdateLandowner(
                    Landowner(
                        name = response.username,
                        landId = response.land_id
                    )
                )
            }
        )
    }

    suspend fun signupFarmer(
        farmerSignupRequest: FarmerSignupRequest
    ): Flow<State<SignupResponse?>> {
        return ApiHandler().makeRequest(
            execute = { authApiService.signupFarmer(farmerSignupRequest) },
            onSuccess = { response ->
                authPreference.saveAuthToken(response.token)
                authPreference.saveRole(FARMER)
            }
        )
    }

    suspend fun signin(
        signinRequest: SigninRequest
    ): Flow<State<SigninResponse?>> {
        return ApiHandler().makeRequest(
            execute = { authApiService.signin(signinRequest) },
            onSuccess = { response ->
                authPreference.saveAuthToken(response.token)
                authPreference.saveRole(response.role)
                Log.d("SIGNIN","repo: $response")
//                if (response.role == LANDOWNER) {
//                    landownerDao.insertOrUpdateLandowner(
//                        Landowner(
//                            name = response.username,
//                            landId = response.landId
//                        )
//                    )
//                }
            }
        )
    }
}