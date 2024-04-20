package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.request.DetectionHistoryRequest
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionResponse
import com.example.hapi.data.remote.response.DiseaseDetectionResponse
import com.example.hapi.util.DETECTION
import com.example.hapi.util.DETECTION_ITEM
import com.example.hapi.util.DETECTION_HISTORY
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface DetectionApiService {

    @Multipart
    @POST(DETECTION)
    suspend fun detectDisease(
        @Part image: MultipartBody.Part,
        @Part("crop") crop: RequestBody
    ): Response<DiseaseDetectionResponse>

    @GET(DETECTION_HISTORY)
    suspend fun getDetectionHistory(
        @Query("id") id: Int
    ): Response<List<DetectionHistoryResponse>>

    @GET(DETECTION_ITEM)
    suspend fun getDetection(@Path("id") id: Int): Response<DetectionResponse>

}