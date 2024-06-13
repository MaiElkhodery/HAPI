package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.detection_history.DetectionOfHistoryDao
import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionResponse
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DetectionHistoryRepository @Inject constructor(
    private val detectionApiService: DetectionApiService,
    private val detectionOfHistoryDao: DetectionOfHistoryDao,
    private val userDataPreference: UserDataPreference
) : ApiHandler() {
    suspend fun fetchDetectionHistory(
        id: Int
    ): Flow<State<Boolean>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    emit(State.Loading)
                    val response = detectionApiService.getDetectionHistory(id)
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            cacheDetectionHistory(response.body()!!)
                            Log.d("DetectionHistoryRepository", "fetchDetectionHistory: ${response.body()}")
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
    }


    suspend fun getDetectionDetails(
        id: Int
    ): Flow<State<DetectionResponse>> {

        return withContext(Dispatchers.IO) {
            ApiHandler().makeRequest(
                execute = {
                    detectionApiService.getDetectionWithId(id)
                }
            )
        }
    }


    private suspend fun cacheDetectionHistory(detectionHistoryList: List<DetectionHistoryResponse>) {

        withContext(Dispatchers.IO) {
            detectionHistoryList.forEach { detectionHistory ->
                detectionOfHistoryDao.insertDetection(
                    DetectionOfHistory(
                        remoteId = detectionHistory.id,
                        username = detectionHistory.username,
                        imageUrl = detectionHistory.image_url,
                        time = detectionHistory.time,
                        date = detectionHistory.date
                    )
                )

            }
        }
    }

    suspend fun getDetectionHistory(): List<DetectionOfHistory>? {
        return withContext(Dispatchers.IO) {
            detectionOfHistoryDao.getAllDetectionHistory()
        }
    }

    suspend fun getLastDetection(): DetectionOfHistory? {
        return withContext(Dispatchers.IO) {
            detectionOfHistoryDao.getLastDetection()
        }

    }

    suspend fun deleteDetectionHistory() {
        withContext(Dispatchers.IO) {
            detectionOfHistoryDao.deleteAll()
        }
    }

    suspend fun getDetectionByUsername(): List<DetectionOfHistory>? {
        return withContext(Dispatchers.IO) {
            detectionOfHistoryDao.getDetectionByUsername(userDataPreference.getUsername())
        }
    }
}