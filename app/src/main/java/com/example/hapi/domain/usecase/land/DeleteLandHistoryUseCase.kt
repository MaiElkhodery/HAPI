package com.example.hapi.domain.usecase.land

import com.example.hapi.data.repository.LandRepository
import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class DeleteLandHistoryUseCase @Inject constructor(
    private val landRepository: LandRepository
) {
    suspend operator fun invoke() {
        landRepository.deleteLandHistory()
    }
}