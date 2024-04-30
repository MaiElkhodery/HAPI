package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.current_detection.CurrentDetectionDao
import com.example.hapi.data.local.room.dao.current_detection.CurrentDiseaseDao
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetection
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionDisease
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionWithDisease
import com.example.hapi.data.remote.api.ApiHandler
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.domain.model.Disease
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.example.hapi.util.createMultiPartBody
import com.example.hapi.util.getCurrentDateAsString
import com.example.hapi.util.getCurrentTimeAsString
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class DetectionRepository @Inject constructor(
    private val detectionApiService: DetectionApiService,
    private val currentDetectionDao: CurrentDetectionDao,
    private val currentDiseaseDao: CurrentDiseaseDao,
    private val userDataPreference: UserDataPreference

) : ApiHandler() {
    suspend fun detectDisease(
        crop: String,
        byteArrayImage: ByteArray
    ): Flow<State<Long>> {
        return flow {
            try {
                val image = createMultiPartBody(byteArrayImage)
                emit(State.Loading)
                val response = detectionApiService.detectDisease(
                    crop = crop.lowercase().toRequestBody("text/plain".toMediaTypeOrNull()),
                    image = image
                )

                if (response.isSuccessful) {

                    deleteCurrentCachedDetection()
                    val detectionId = saveDetection(
                        username = userDataPreference.getUsername(),
                        date = getCurrentDateAsString(),
                        time = getCurrentTimeAsString(),
                        confidence = response.body()!!.confidence,
                        isHealthy = response.body()!!.isHealthy,
                        crop = crop,
                        byteArrayImage = byteArrayImage
                    )
                    if (!response.body()!!.isHealthy)
                        saveDisease(response.body()!!.diseases, detectionId.toInt())
                    emit(State.Success(detectionId))
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        SignupErrorInfo::class.java
                    )
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                emit(handleException(e))
            }
        }
    }

    private suspend fun saveDisease(
        diseases: List<Disease>,
        detectionId: Int
    ) {
        withContext(Dispatchers.IO) {
            val diseaseList = diseases.map { disease ->
                CurrentDetectionDisease(
                    name = disease.name,
                    confidence = disease.confidence,
                    infoLink = disease.infoLink,
                    detectionId = detectionId
                )
            }
            currentDiseaseDao.insertListOfDiseases(diseaseList)
        }
    }

    private suspend fun saveDetection(
        username: String,
        date: String,
        time: String,
        confidence: Float,
        isHealthy: Boolean,
        crop: String,
        byteArrayImage: ByteArray
    ): Long {
        return withContext(Dispatchers.IO) {
            currentDetectionDao.insertDetection(
                CurrentDetection(
                    detectionMaker = username,
                    date = date,
                    time = time,
                    confidence = confidence,
                    isHealthy = isHealthy,
                    crop = crop,
                    image = byteArrayImage
                )
            )
        }
    }

    suspend fun getLocalCurrentDetectionById(detectionId: Int): CurrentDetectionWithDisease {
        return withContext(Dispatchers.IO) {
            currentDetectionDao.getDetectionWithDiseasesById(detectionId)
        }
    }

    private suspend fun deleteCurrentCachedDetection() {
        withContext(Dispatchers.IO) {
            currentDetectionDao.deleteAll()
            currentDiseaseDao.deleteAll()
        }
    }
}