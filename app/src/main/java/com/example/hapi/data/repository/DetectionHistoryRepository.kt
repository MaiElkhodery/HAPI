package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.room.dao.history.DetectionHistoryItemDao
import com.example.hapi.data.local.room.dao.history.DiseaseHistoryDao
import com.example.hapi.data.local.room.entities.history.DetectionDiseaseHistory
import com.example.hapi.data.local.room.entities.history.DetectionHistoryItem
import com.example.hapi.data.local.room.entities.history.DetectionHistoryWithDiseases
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionItemResponse
import com.example.hapi.domain.model.State
import com.example.hapi.util.downloadImage
import com.example.hapi.util.toByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DetectionHistoryRepository @Inject constructor(
    private val detectionHistoryDao: DetectionHistoryItemDao,
    private val diseaseHistoryDao: DiseaseHistoryDao,
    private val detectionApiService: DetectionApiService
) : ApiHandler() {
    suspend fun getDetectionHistory(): Flow<State<List<DetectionHistoryResponse>>> {
        return ApiHandler().makeRequest(
            execute = {
                detectionApiService.getDetectionHistory()
            },
            onSuccess = {
                Log.d("DetectionHistoryRepository", "getDetectionHistory: $it")
            }
        )
    }

    suspend fun getAndSaveLastFiveDetections(): Boolean {
        return try {
            val response = detectionApiService.getLastFiveDetections()
            if (response.isSuccessful) {
                saveDetections(response.body()!!)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }

    }

    suspend fun getDetection(
        id: Int
    ): Flow<State<DetectionItemResponse>> {

        return ApiHandler().makeRequest(
            execute = {
                detectionApiService.getDetection(id)
            },
            onSuccess = {
               Log.d("DetectionHistoryRepository", "getDetection: $it")
            }
        )

    }

    suspend fun getLastDetection(): Flow<State<DetectionHistoryResponse>> {

        return ApiHandler().makeRequest(
            execute = {
                detectionApiService.getLastDetection()
            },
            onSuccess = {
                Log.d("DetectionHistoryRepository", "getLastDetection: $it")
            }
        )
    }

    private suspend fun saveDetections(
        listOfDetections: List<DetectionItemResponse>
    ) {
        deleteAllDetections()
        withContext(Dispatchers.IO) {
            listOfDetections.forEach { detectionResponse ->
                val imageByteArray = downloadImage(detectionResponse.image_url)?.toByteArray()!!

                val detectionId = detectionHistoryDao.insertDetectionList(
                    DetectionHistoryItem(
                        name = detectionResponse.username,
                        date = detectionResponse.date,
                        time = detectionResponse.time,
                        imageByteArray = imageByteArray,
                        isHealthy = detectionResponse.detection.isHealthy,
                        confidence = detectionResponse.detection.confidence
                    )
                )
                detectionResponse.detection.diseases.forEach { disease ->
                    diseaseHistoryDao.insertDisease(
                        DetectionDiseaseHistory(
                            detectionId = detectionId.toInt(),
                            name = disease.name,
                            confidence = disease.confidence,
                            infoLink = disease.infoLink
                        )

                    )
                }
            }
        }
    }

    suspend fun getLocalDetections():List<DetectionHistoryWithDiseases>{
       return withContext(Dispatchers.IO){
            detectionHistoryDao.getALlDetectionWithDiseases()
        }
    }

    private suspend fun deleteAllDetections(){
        withContext(Dispatchers.IO){
            detectionHistoryDao.deleteAll()
            diseaseHistoryDao.deleteAll()
        }
    }
}