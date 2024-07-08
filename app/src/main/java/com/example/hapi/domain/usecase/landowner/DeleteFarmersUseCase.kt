package com.example.hapi.domain.usecase.landowner

import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class DeleteFarmersUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke() = landownerRepository.deleteFarmers()
}