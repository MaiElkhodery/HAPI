package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.land_history.LandDataDao
import com.example.hapi.data.local.room.entities.land_history.LandData
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.data.remote.request.SelectCropRequest
import com.example.hapi.data.remote.response.CropRecommendationResponse
import com.example.hapi.data.remote.response.LandHistoryResponse
import com.example.hapi.data.remote.response.LastFarmerResponse
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
    private val userDataPreference: UserDataPreference,
    private val landDataDao: LandDataDao
) : ApiHandler() {
    suspend fun cropRecommendation(): Flow<State<CropRecommendationResponse?>> {
        return ApiHandler().makeRequest(
            execute = { landownerApiService.getRecommendedCrops() },
        )
    }

    suspend fun uploadSelectedCrop(crop: String): Flow<State<Unit>> {
        return ApiHandler().makeRequest(
            execute = {
                landownerApiService.uploadSelectedCrop(SelectCropRequest(crop = crop)).apply {
                    Log.d("LandownerRepository", "uploadSelectedCrop: $this")
                }
            },
            onSuccess = {
                userDataPreference.saveCrop(crop)
            }
        )

    }

    suspend fun getAndSaveAllLandHistory(
        lastSavedId: Int,
    ): Flow<State<Boolean>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    emit(State.Loading)
                    val response = landownerApiService.getLandHistory(lastSavedId)
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            saveLandHistory(response.body()!!)
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
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun saveLandHistory(landData: List<LandHistoryResponse>) {
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

    suspend fun getLastLandHistoryItem(): LandData? {
        return withContext(Dispatchers.IO) {
            landDataDao.getLastLandDataByRemoteId()
        }
    }

    suspend fun getAllSavedLandHistory(): List<LandData>? {
        return withContext(Dispatchers.IO) {
            landDataDao.getAllLandData()
        }
    }

    suspend fun getAndSaveLandData(): Flow<State<Boolean>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    emit(State.Loading)
                    val response = landownerApiService.getLandData()
                    if (response.isSuccessful) {
                        userDataPreference.saveWaterLevel(response.body()!!.water_level)
                        userDataPreference.saveNPK(
                            "${response.body()!!.npk.N}% -${response.body()!!.npk.P}% -${response.body()!!.npk.K}%"
                        )
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
                    e.printStackTrace()
                }
            }
        }

    }

    suspend fun getLastFarmer(): Flow<State<LastFarmerResponse>> {
        return withContext(Dispatchers.IO) {
            ApiHandler().makeRequest(
                execute = {
                    landownerApiService.getLastFarmer().apply {
                        Log.d("LandownerRepository", "getLastFarmer: ${this.body()}")
                    }
                }
            )
        }
    }


}