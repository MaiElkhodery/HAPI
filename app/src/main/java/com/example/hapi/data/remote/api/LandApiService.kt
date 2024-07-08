package com.example.hapi.data.remote.api

import com.example.hapi.data.remote.response.LandHistoryResponse
import com.example.hapi.util.LAND_HISTORY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LandApiService {
    @GET(LAND_HISTORY)
    suspend fun getLandHistory(
        @Query("id") id: Int
    ): Response<List<LandHistoryResponse>>
}