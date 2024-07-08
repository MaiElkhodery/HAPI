package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.domain.model.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDetectionHistoryUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke(
        id: Int
    ): Flow<State<Boolean>> {
        return detectionHistoryRepository.fetchDetectionHistory(id)
    }

}
