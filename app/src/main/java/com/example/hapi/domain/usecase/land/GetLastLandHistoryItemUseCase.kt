package com.example.hapi.domain.usecase.land

import com.example.hapi.data.repository.LandRepository
import javax.inject.Inject

class GetLastLandHistoryItemUseCase @Inject constructor(
    private val landRepository: LandRepository
) {
    suspend operator fun invoke() = landRepository.getLastLandHistoryItem()
}
