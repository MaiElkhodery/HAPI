package com.example.hapi.domain.usecase

import com.example.hapi.data.model.State
import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.response.AuthResponse
import com.example.hapi.data.repository.FarmerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FarmerSignupUseCase @Inject constructor(
    private val farmerRepository: FarmerRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        username: String,
        password: String,
        landId: String
    ): Flow<State<AuthResponse?>> {
        return farmerRepository.signup(
            FarmerSignupRequest(
                role = "farmer",
                phone_number = phoneNumber,
                username = username,
                password = password,
                land_id = landId
            )
        )
    }
}