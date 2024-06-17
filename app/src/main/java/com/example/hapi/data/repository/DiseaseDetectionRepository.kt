package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.CurrentDetectionDao
import com.example.hapi.data.local.room.entities.CurrentDetection
import com.example.hapi.data.remote.api.DetectionApiService
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

class DiseaseDetectionRepository @Inject constructor(
    private val detectionApiService: DetectionApiService,
    private val currentDetectionDao: CurrentDetectionDao,
    private val userDataPreference: UserDataPreference,
) {
    suspend fun detectDisease(
        crop: String,
        byteArrayImage: ByteArray,
        imageLocalUrl: String
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
                        certainty = response.body()!!.certainty,
                        crop = crop,
                        imageLocalUrl = imageLocalUrl,
                        diseaseName = response.body()!!.disease_name,
                        link = response.body()!!.info_link
                    )
                    emit(State.Success(detectionId))
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

    private suspend fun saveDetection(
        username: String,
        date: String,
        time: String,
        certainty: Float,
        diseaseName: String,
        link: String,
        crop: String,
        imageLocalUrl: String
    ): Long {
        return withContext(Dispatchers.IO) {
            currentDetectionDao.insertDetection(
                CurrentDetection(
                    username = username,
                    date = date,
                    time = time,
                    certainty = certainty,
                    crop = crop,
                    imageLocalUrl = imageLocalUrl,
                    diseaseName = diseaseName,
                    link = link
                )
            )
        }
    }

    suspend fun getLocalCurrentDetectionById(detectionId: Int): CurrentDetection {
        return withContext(Dispatchers.IO) {
            currentDetectionDao.getDetectionWithDiseasesById(detectionId)
        }
    }

    private suspend fun deleteCurrentCachedDetection() {
        withContext(Dispatchers.IO) {
            currentDetectionDao.deleteAll()
        }
    }
}