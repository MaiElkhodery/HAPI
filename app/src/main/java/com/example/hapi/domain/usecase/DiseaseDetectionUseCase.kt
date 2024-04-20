package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.DetectionRepository
import com.example.hapi.domain.model.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiseaseDetectionUseCase @Inject constructor(
    private val detectionRepository: DetectionRepository
) {
    suspend operator fun invoke(
        crop: String,
        byteArrayImage: ByteArray
    ): Flow<State<Long>> {
        return detectionRepository.detectDisease(crop, byteArrayImage)
    }

}
