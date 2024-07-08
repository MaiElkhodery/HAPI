package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.repository.DetectionHistoryRepository
import javax.inject.Inject

class DeleteDetectionHistoryUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke() {
        detectionHistoryRepository.deleteDetectionHistory()
    }
}