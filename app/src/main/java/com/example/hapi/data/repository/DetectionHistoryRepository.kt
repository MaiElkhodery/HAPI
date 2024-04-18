package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.room.dao.detection_history.DetectionOfHistoryDao
import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.request.DetectionHistoryRequest
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionResponse
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.example.hapi.util.downloadImage
import com.example.hapi.util.toByteArray
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DetectionHistoryRepository @Inject constructor(
    private val detectionApiService: DetectionApiService,
    private val detectionOfHistoryDao: DetectionOfHistoryDao
) : ApiHandler() {
    suspend fun getAndSaveDetectionHistory(
        id: Int
    ): Flow<Boolean> {
        return flow {
            try {
                val response = detectionApiService.getDetectionHistory(
                    DetectionHistoryRequest(id)
                )
                if (response.isSuccessful) {
                    saveDetectionHistory(response.body()!!)
                    emit(true)
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        SignupErrorInfo::class.java
                    )
                    emit(false)
                    Log.d("DetectionHistoryRepository", "getAndSaveDetectionHistory: $error")
                }
            } catch (e: Exception) {
                emit(false)
                Log.d("DetectionHistoryRepository", "getAndSaveDetectionHistory: $e")
            }
        }
    }


    suspend fun getRemoteDetectionDetailsById(
        id: Int
    ): Flow<State<DetectionResponse>> {

        return ApiHandler().makeRequest(
            execute = {
                detectionApiService.getDetection(id)
            },
            onSuccess = {
                Log.d("DetectionHistoryRepository", "getDetection: $it")
            }
        )

    }

    suspend fun getDetectionByIdFromLocal(id: Int): DetectionOfHistory {
        return detectionOfHistoryDao.getDetectionById(id)
    }


    private suspend fun saveDetectionHistory(detectionHistoryList: List<DetectionHistoryResponse>) {

        detectionOfHistoryDao.insertDetectionList(
            detectionHistoryList.map {
                DetectionOfHistory(
                    remoteId = it.id,
                    username = it.username,
                    imageByteArray = downloadImage(it.image_url)!!.toByteArray(),
                    time = it.time,
                    date = it.date
                )
            }
        )
    }

    suspend fun getDetectionHistoryFromLocal(): List<DetectionOfHistory> {
        return detectionOfHistoryDao.getAllDetectionHistory()
    }

    suspend fun getNewestDetection(): DetectionOfHistory {
        return detectionOfHistoryDao.getNewestDetection()
    }

}