package com.example.hapi.data.repository

import com.example.hapi.data.local.room.dao.land_history.LandDataDao
import com.example.hapi.data.local.room.entities.land_history.LandData
import com.example.hapi.data.remote.api.LandApiService
import com.example.hapi.data.remote.response.LandHistoryResponse
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LandRepository @Inject constructor(
    private val landApiService: LandApiService,
    private val landDataDao: LandDataDao
) {
    suspend fun fetchLandHistory(
        lastSavedId: Int,
    ): Flow<State<Boolean>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    emit(State.Loading)
                    val response = landApiService.getLandHistory(lastSavedId)
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

    suspend fun getLandHistory(): List<LandData>? {
        return withContext(Dispatchers.IO) {
            landDataDao.getAllLandData()
        }
    }

    suspend fun deleteLandHistory() {
        withContext(Dispatchers.IO) {
            landDataDao.deleteAll()
        }
    }

    suspend fun getLandDataByActionType(actionType: String): List<LandData>? {
        return withContext(Dispatchers.IO) {
            landDataDao.getLandDataByActionType(actionType)
        }
    }
}