package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.request.SelectCropRequest
import com.example.hapi.data.remote.response.CropRecommendationResponse
import com.example.hapi.data.remote.response.LandDataResponse
import com.example.hapi.data.remote.response.LastFarmerResponse
import com.example.hapi.util.FARMERS
import com.example.hapi.util.LAND_DATA
import com.example.hapi.util.LAST_FARMER
import com.example.hapi.util.RECOMMENDATIONS
import com.example.hapi.util.SELECTED_CROP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LandownerApiService {
    @POST(RECOMMENDATIONS)
    suspend fun getRecommendedCrops(
    ): Response<CropRecommendationResponse>

    @POST(SELECTED_CROP)
    suspend fun uploadSelectedCrop(
        @Body crop: SelectCropRequest
    ): Response<Unit>

    @GET(LAND_DATA)
    suspend fun getTanksData(): Response<LandDataResponse>

    @GET(LAST_FARMER)
    suspend fun getLastFarmer():Response<LastFarmerResponse>

    @GET(FARMERS)
    suspend fun getFarmers():Response<List<String>>

}