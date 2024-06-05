package com.example.hapi.domain.usecase.land

import com.example.hapi.data.repository.LandRepository
import javax.inject.Inject

class GetLandHistoryByActionTypeUseCase @Inject constructor(
    private val landRepository: LandRepository
) {
    suspend operator fun invoke(actionType: String) =
        landRepository.getLandDataByActionType(actionType)
}