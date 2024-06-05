package com.example.hapi.domain.usecase.landowner

import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class GetFarmersUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke():List<String>{
        val list = mutableListOf<String>()
        landownerRepository.getFarmers().forEach {
            list.add(it.name)
        }
        return list
    }
}