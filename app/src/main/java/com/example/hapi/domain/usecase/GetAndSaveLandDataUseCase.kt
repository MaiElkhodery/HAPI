package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class GetAndSaveLandDataUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke(id: Int) = landownerRepository.getAndSaveLandDataHistory(id)
}