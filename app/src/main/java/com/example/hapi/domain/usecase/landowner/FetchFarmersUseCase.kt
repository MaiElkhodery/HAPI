package com.example.hapi.domain.usecase.landowner

import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class FetchFarmersUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
){
    suspend operator fun invoke() = landownerRepository.fetchFarmers()
}