package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.room.dao.history.DetectionDao
import com.example.hapi.data.local.room.dao.history.DiseaseDao
import com.example.hapi.data.local.room.entities.history.Disease
import com.example.hapi.data.local.room.entities.history.Detection
import com.example.hapi.data.local.room.entities.history.DetectionWithDiseases
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.remote.response.DetectionResponse
import com.example.hapi.domain.model.State
import com.example.hapi.util.downloadImage
import com.example.hapi.util.toByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DetectionHistoryRepository @Inject constructor(
    private val detectionDao: DetectionDao,
    private val diseaseDao: DiseaseDao,
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
                saveLastFiveDetections(response.body()!!)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }

    }

    suspend fun getRemoteDetectionById(
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

    suspend fun getRemoteLastDetection(): Flow<State<DetectionHistoryResponse>> {

        return ApiHandler().makeRequest(
            execute = {
                detectionApiService.getLastDetection()
            },
            onSuccess = {
                Log.d("DetectionHistoryRepository", "getLastDetection: $it")
            }
        )
    }

    private suspend fun saveLastFiveDetections(
        listOfDetections: List<DetectionResponse>
    ) {
        deleteAllDetections()
        withContext(Dispatchers.IO) {
            listOfDetections.forEach { detectionResponse ->
                val imageByteArray = downloadImage(detectionResponse.image_url)?.toByteArray()!!

                val detectionId = detectionDao.insertDetectionList(
                    Detection(
                        name = detectionResponse.username,
                        date = detectionResponse.date,
                        time = detectionResponse.time,
                        imageByteArray = imageByteArray,
                        isHealthy = detectionResponse.detection.isHealthy,
                        confidence = detectionResponse.detection.confidence
                    )
                )
                detectionResponse.detection.diseases.forEach { disease ->
                    diseaseDao.insertDisease(
                        Disease(
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

    suspend fun getSavedLastFiveDetections():List<DetectionWithDiseases>{
       return withContext(Dispatchers.IO){
            detectionDao.getALlDetectionWithDiseases()
        }
    }

    private suspend fun deleteAllDetections(){
        withContext(Dispatchers.IO){
            detectionDao.deleteAll()
            diseaseDao.deleteAll()
        }
    }
}