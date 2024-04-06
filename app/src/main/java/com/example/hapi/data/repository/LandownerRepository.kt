package com.example.hapi.data.repository

import com.example.hapi.data.local.AuthPreference
import com.example.hapi.data.model.State
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.data.remote.request.SelectCropRequest
import com.example.hapi.data.remote.response.CropRecommendationResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LandownerRepository @Inject constructor(
    private val landownerApiService: LandownerApiService,
    private val authPreference: AuthPreference
) : ApiHandler() {
    suspend fun cropRecommendation(): Flow<State<CropRecommendationResponse?>> {
        return ApiHandler().makeRequest (
            execute = { landownerApiService.getRecommendedCrops() },
        )
    }

    suspend fun uploadSelectedCrop(crop: String): Flow<State<Unit?>> {
        return ApiHandler().makeRequest (
            execute = { landownerApiService.uploadSelectedCrop(SelectCropRequest(crop = crop)) },
            onSuccess = { authPreference.saveHaveSelectedCrop(true) }
        )
    }
}