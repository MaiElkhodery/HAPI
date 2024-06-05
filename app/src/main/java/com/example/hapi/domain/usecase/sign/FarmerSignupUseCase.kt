package com.example.hapi.domain.usecase.sign

import com.example.hapi.domain.model.State
import com.example.hapi.data.remote.request.FarmerSignupRequest
import com.example.hapi.data.remote.response.SignupResponse
import com.example.hapi.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FarmerSignupUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        username: String,
        password: String,
        landId: String
    ): Flow<State<SignupResponse?>> {
        return authRepository.signupFarmer(
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