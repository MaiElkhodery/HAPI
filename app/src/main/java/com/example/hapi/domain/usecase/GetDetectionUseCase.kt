package com.example.hapi.domain.usecase

import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionWithDisease
import com.example.hapi.data.repository.DetectionRepository
import javax.inject.Inject

class GetDetectionUseCase @Inject constructor(
    private val detectionRepository: DetectionRepository
) {
    suspend operator fun invoke(
        id: Int
    ): CurrentDetectionWithDisease {
        return detectionRepository.getDetectionWithDiseasesById(id)
    }

}