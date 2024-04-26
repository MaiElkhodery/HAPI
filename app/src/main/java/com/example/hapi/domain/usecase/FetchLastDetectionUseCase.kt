package com.example.hapi.domain.usecase

import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import com.example.hapi.data.repository.DetectionHistoryRepository
import javax.inject.Inject

class FetchLastDetectionUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke(
    ): DetectionOfHistory? {
        return detectionHistoryRepository.getNewestDetection()
    }

}
