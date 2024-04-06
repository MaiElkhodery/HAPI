package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.request.SelectCropRequest
import com.example.hapi.data.remote.response.CropRecommendationResponse
import com.example.hapi.util.RECOMMENDATIONS
import com.example.hapi.util.SELECTED_CROP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LandownerApiService {
    @POST(RECOMMENDATIONS)
    suspend fun getRecommendedCrops(
    ): Response<CropRecommendationResponse>

    @POST(SELECTED_CROP)
    suspend fun uploadSelectedCrop(
        @Body crop: SelectCropRequest
    ): Response<Unit>
}