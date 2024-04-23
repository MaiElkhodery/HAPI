package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.land_history.LandDataDao
import com.example.hapi.data.local.room.entities.land_history.LandData
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.data.remote.request.SelectCropRequest
import com.example.hapi.data.remote.response.CropRecommendationResponse
import com.example.hapi.data.remote.response.LandDataResponse
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LandownerRepository @Inject constructor(
    private val landownerApiService: LandownerApiService,
    private val authPreference: UserDataPreference,
    private val landDataDao: LandDataDao
) : ApiHandler() {
    suspend fun cropRecommendation(): Flow<State<CropRecommendationResponse?>> {
        return ApiHandler().makeRequest(
            execute = { landownerApiService.getRecommendedCrops() },
        )
    }

    suspend fun uploadSelectedCrop(crop: String): Flow<State<Unit?>> {
        return ApiHandler().makeRequest(
            execute = { landownerApiService.uploadSelectedCrop(SelectCropRequest(crop = crop)) },
            onSuccess = { authPreference.saveHaveSelectedCrop(true) }
        )
    }

    suspend fun getAndSaveLandDataHistory(
        id: Int
    ): Flow<State<Boolean>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    emit(State.Loading)
                    Log.d("LandownerRepository", "getAndSaveLandDataHistory: $id")
                    val response = landownerApiService.getLandData(id).apply {
                        Log.d("LandownerRepository", "getAndSaveLandDataHistory: $this")
                    }
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            saveLandDataHistory(response.body()!!)
                            Log.d(
                                "LandownerRepository",
                                "getAndSaveLandDataHistory: ${response.body()}"
                            )
                        }
                        emit(State.Success(true))
                    } else {
                        val error = Gson().fromJson(
                            response.errorBody()?.string(),
                            SignupErrorInfo::class.java
                        )
                        emit(State.Error(error))
                    }
                } catch (e: Exception) {
                    emit(State.Exception(e.message.toString()))
                    Log.d("LandownerRepository", "getAndSaveLandDataHistory: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun saveLandDataHistory(landData: List<LandDataResponse>) {
        withContext(Dispatchers.IO) {
            landData.forEach { landData ->
                landDataDao.upsert(
                    LandData(
                        remote_id = landData.id,
                        date = landData.date,
                        time = landData.time,
                        action_type = landData.action_type
                    )
                )
            }
        }
    }

    suspend fun getLastLandDataHistory(): LandData? {
        return withContext(Dispatchers.IO) {
            landDataDao.getLastLandDataByRemoteId()
        }
    }

    suspend fun getLandDataHistory(): List<LandData>? {
        return withContext(Dispatchers.IO) {
            landDataDao.getAllLandData()
        }
    }
}