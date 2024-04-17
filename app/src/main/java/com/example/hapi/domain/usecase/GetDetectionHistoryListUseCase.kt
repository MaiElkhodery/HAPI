package com.example.hapi.domain.usecase

import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.domain.model.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetectionHistoryListUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke(): Flow<State<List<DetectionHistoryResponse>>> {
        return detectionHistoryRepository.getDetectionHistory()
    }

}
