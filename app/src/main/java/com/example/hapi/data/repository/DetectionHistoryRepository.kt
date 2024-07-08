package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.DetectionOfHistoryDao
import com.example.hapi.data.local.room.entities.DetectionOfHistory
import com.example.hapi.data.remote.ApiHandler
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionResponse
import com.example.hapi.domain.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
                        userDataPreference.saveLastDetectionHistoryId(responseBody.first().id.toString())
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

        return apiHandler.makeRequest(
            execute = {
                detectionApiService.getDetectionWithId(id)
            }
        )

    }


    private suspend fun cacheDetectionHistory(detectionHistoryList: List<DetectionHistoryResponse>) {

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

    suspend fun getDetectionHistory(): List<DetectionOfHistory>? {
        return detectionOfHistoryDao.getAllDetectionHistory()
    }

    suspend fun getLastDetection(): DetectionOfHistory? {
        return detectionOfHistoryDao
            .getLastDetection(
                userDataPreference.getLastDetectionHistoryId().toInt()
            )
    }

    suspend fun deleteDetectionHistory() {
        detectionOfHistoryDao.deleteAll()
    }

    suspend fun getDetectionByUsername(): List<DetectionOfHistory>? {
        return detectionOfHistoryDao.getDetectionByUsername(userDataPreference.getUsername())
    }
}