package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import com.example.hapi.data.repository.DetectionHistoryRepository
import javax.inject.Inject

class GetDetectionHistoryByUsernameUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke(): List<DetectionOfHistory>? {
        return detectionHistoryRepository.getDetectionByUsername()
    }

}
