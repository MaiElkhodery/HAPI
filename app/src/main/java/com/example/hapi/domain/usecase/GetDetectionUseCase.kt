package com.example.hapi.domain.usecase

import com.example.hapi.data.local.room.entities.landowner.LandownerDetectionWithDiseases
import com.example.hapi.data.repository.DetectionRepository
import javax.inject.Inject

class GetDetectionUseCase @Inject constructor(
    private val detectionRepository: DetectionRepository
) {
    suspend operator fun invoke(
        id: Int
    ): LandownerDetectionWithDiseases {
        return detectionRepository.getDetectionWithDiseasesById(id)
    }

}