package com.example.hapi.domain.usecase.land

import com.example.hapi.data.repository.LandRepository
import javax.inject.Inject

class GetLandHistoryUseCase @Inject constructor(
    private val landRepository: LandRepository
) {
    suspend operator fun invoke() = landRepository.getLandHistory()
}