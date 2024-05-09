package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionWithDisease
import com.example.hapi.data.repository.DiseaseDetectionRepository
import javax.inject.Inject

class GetCurrentDetectionUseCase @Inject constructor(
    private val diseaseDetectionRepository: DiseaseDetectionRepository
) {
    suspend operator fun invoke(
        id: Int
    ): CurrentDetectionWithDisease {
        return diseaseDetectionRepository.getLocalCurrentDetectionById(id)
    }

}