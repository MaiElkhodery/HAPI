package com.example.hapi.domain.usecase

import android.util.Log
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.domain.model.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAndSaveDetectionHistoryUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
) {
    suspend operator fun invoke(
        id: Int
    ): Flow<State<Boolean>> {
        Log.d("GetAndSaveDetectionHistoryUseCase", "invoke: $id")
        return detectionHistoryRepository.getAndSaveDetectionHistory(id)
    }

}
