package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.DetectionHistoryRepository
import javax.inject.Inject

class LocalDetectionsUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke() = detectionHistoryRepository.getLocalDetections()
}