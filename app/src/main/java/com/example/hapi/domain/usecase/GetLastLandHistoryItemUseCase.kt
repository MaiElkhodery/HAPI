package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class GetLastLandHistoryItemUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke() = landownerRepository.getLastLandHistoryItem()
}
