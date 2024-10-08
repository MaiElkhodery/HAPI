package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.local.room.entities.DetectionOfHistory
import com.example.hapi.data.repository.DetectionHistoryRepository
import javax.inject.Inject

class GetLastDetectionUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke(
    ): DetectionOfHistory? {
        return detectionHistoryRepository.getLastDetection()
    }

}
