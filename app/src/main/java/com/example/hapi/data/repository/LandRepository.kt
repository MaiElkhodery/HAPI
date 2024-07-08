package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.LandDataDao
import com.example.hapi.data.local.room.entities.LandData
import com.example.hapi.data.remote.api.LandApiService
import com.example.hapi.data.remote.response.LandHistoryResponse
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LandRepository @Inject constructor(
    private val landApiService: LandApiService,
    private val landDataDao: LandDataDao,
    private val userDataPreference: UserDataPreference
) {
    suspend fun fetchLandHistory(
        lastSavedId: Int,
    ): Flow<State<Boolean>> {
        return flow {
            try {
                emit(State.Loading)
                val response = landApiService.getLandHistory(lastSavedId)
                if (response.isSuccessful) {
                    if (!response.body().isNullOrEmpty()) {
                        saveLandHistory(response.body()!!)
                        userDataPreference.saveLastLandDataHistoryId(
                            response.body()!!.first().id.toString()
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
            }
        }
    }

    private suspend fun saveLandHistory(landData: List<LandHistoryResponse>) {
        landData.forEach { data ->
            landDataDao.upsert(
                LandData(
                    remote_id = data.id,
                    date = data.date,
                    time = data.time,
                    action_type = data.action_type
                )
            )
        }
    }

    suspend fun getLastLandHistoryItem(): LandData? {
        return landDataDao.getLastLandDataByRemoteId(
            userDataPreference.getLastLandDataHistoryId().toInt()
        )

    }

    suspend fun getLandHistory(): List<LandData>? {
        return landDataDao.getAllLandData()
    }

    suspend fun deleteLandHistory() {
        landDataDao.deleteAll()
    }

    suspend fun getLandDataByActionType(actionType: String): List<LandData>? {
        return landDataDao.getLandDataByActionType(actionType)
    }

}