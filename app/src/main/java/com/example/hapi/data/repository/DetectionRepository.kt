package com.example.hapi.data.repository

import android.util.Log
import com.example.hapi.data.local.room.dao.landowner.LandownerDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDetectionDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDiseaseDao
import com.example.hapi.data.local.room.entities.landowner.LandownerDetection
import com.example.hapi.data.local.room.entities.landowner.LandownerDetectionDisease
import com.example.hapi.data.local.room.entities.landowner.LandownerDetectionWithDiseases
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
    private val detectionDao: LandownerDetectionDao,
    private val diseaseDao: LandownerDiseaseDao,
    private val landownerDao: LandownerDao

) : ApiHandler() {
    suspend fun detectDisease(
        crop: String,
        byteArrayImage: ByteArray
    ): Flow<State<Long>> {
        return flow {
            try {
                val image = createMultiPartBody(byteArrayImage)
                Log.d("DetectRequest", "crop: $crop, image: $image")
                emit(State.Loading)
                val response = detectionApiService.detectDisease(
                    crop = crop.lowercase().toRequestBody("text/plain".toMediaTypeOrNull()),
                    image = image
                )
                Log.d(
                    "DetectResponse",
                    "response body: ${response.body()}, response code: ${response.code()}"
                )

                if (response.isSuccessful) {
                    val detectionId = saveDetection(
//                    username = landownerDao.getLandowner()!!.name,
                        username = "landowner",
                        date = getCurrentDateAsString(),
                        time = getCurrentTimeAsString(),
                        confidence = response.body()!!.confidence,
                        isHealthy = response.body()!!.isHealthy,
                        crop = crop,
                        byteArrayImage = byteArrayImage
                    )
                    Log.d("DetectionId", "detectionId: $detectionId")
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
            diseaseDao.deleteAll()
            val diseaseList = diseases.map { disease ->
                LandownerDetectionDisease(
                    name = disease.name,
                    confidence = disease.confidence,
                    infoLink = disease.infoLink,
                    detectionId = detectionId
                )
            }
            diseaseDao.insertListOfDiseases(diseaseList)
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
            detectionDao.deleteAll()
            detectionDao.insertDetection(
                LandownerDetection(
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

    suspend fun getDetectionWithDiseasesById(detectionId: Int): LandownerDetectionWithDiseases {
        return detectionDao.getDetectionWithDiseasesById(detectionId)
    }
}