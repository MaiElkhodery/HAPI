package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.DetectionOfHistoryDao
import com.example.hapi.data.local.room.entities.DetectionOfHistory
import com.example.hapi.data.remote.ApiHandler
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionResponse
import com.example.hapi.domain.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DetectionHistoryRepository @Inject constructor(
    private val detectionApiService: DetectionApiService,
    private val detectionOfHistoryDao: DetectionOfHistoryDao,
    private val userDataPreference: UserDataPreference,
    private val apiHandler: ApiHandler
) {
    suspend fun fetchDetectionHistory(
        id: Int
    ): Flow<State<Boolean>> {
        return flow {
            apiHandler.makeRequest(
                execute = { detectionApiService.getDetectionHistory(id) },
                onSuccess = { responseBody ->
                    if (responseBody.isNotEmpty()) {
                        cacheDetectionHistory(responseBody)
                        Log.d("DetectionHistoryRepository", "fetchDetectionHistory: $responseBody")
                        Log.d("DetectionHistoryRepository", "lastId : $id")
                    }
                }
            ).collect { state ->
                emit(
                    when (state) {
                        is State.Success -> State.Success(true)
                        is State.Error -> State.Error(state.error)
                        is State.Exception -> State.Exception(state.msg)
                        State.Loading -> State.Loading
                    }
                )
            }
        }

    }


    suspend fun getDetectionDetails(
        id: Int
    ): Flow<State<DetectionResponse>> {

        return withContext(Dispatchers.IO) {
            apiHandler.makeRequest(
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