package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.DetectionHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAndSaveDetectionHistoryUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke(
        id: Int
    ): Flow<Boolean> {
        return detectionHistoryRepository.getAndSaveDetectionHistory(id)
    }

}
