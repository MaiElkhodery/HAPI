package com.example.hapi.domain.usecase.land

import com.example.hapi.data.repository.LandRepository
import javax.inject.Inject

class FetchLandHistoryUseCase @Inject constructor(
    private val landRepository: LandRepository
) {
    suspend operator fun invoke(id: Int) = landRepository.fetchLandHistory(id)
}