package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionItemResponse
import com.example.hapi.data.remote.response.DetectionResponse
import com.example.hapi.util.DETECTION
import com.example.hapi.util.DETECTION_ITEM
import com.example.hapi.util.LANDOWNER_DETECTION_HISTORY
import com.example.hapi.util.LAST_DETECTION
import com.example.hapi.util.LAST_Five_DETECTION
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface DetectionApiService {

    @Multipart
    @POST(DETECTION)
    suspend fun detectDisease(
        @Part image: MultipartBody.Part,
        @Part("crop") crop: RequestBody
    ): Response<DetectionResponse>

    @GET(LANDOWNER_DETECTION_HISTORY)
    suspend fun getDetectionHistory(): Response<List<DetectionHistoryResponse>>

    @GET("$DETECTION_ITEM/{id}")
    suspend fun getDetection(@Path("id") id: Int): Response<DetectionItemResponse>

    @GET(LAST_DETECTION)
    suspend fun getLastDetection(): Response<DetectionHistoryResponse>

    @GET(LAST_Five_DETECTION)
    suspend fun getLastFiveDetections(): Response<List<DetectionItemResponse>>
}