package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.DetectionHistoryRepository
import javax.inject.Inject

class DetectionItemUseCase @Inject constructor(
    private val detectionHistoryRepository: DetectionHistoryRepository
){
    suspend operator fun invoke(id:Int) = detectionHistoryRepository.getDetection(id)
}