package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.repository.DiseaseDetectionRepository
import com.example.hapi.domain.model.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetectDiseaseUseCase @Inject constructor(
    private val detectionRepository: DiseaseDetectionRepository
) {
    suspend operator fun invoke(
        crop: String,
        byteArrayImage: ByteArray
    ): Flow<State<Long>> {
        return detectionRepository.detectDisease(crop, byteArrayImage)
    }

}
