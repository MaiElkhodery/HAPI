package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class GetLastLandDataUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke() = landownerRepository.getLastLandDataHistory()
}
