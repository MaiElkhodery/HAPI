package com.example.hapi.domain.usecase.sign

import com.example.hapi.domain.model.State
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.data.remote.response.SignupResponse
import com.example.hapi.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LandownerSignupUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        username: String,
        password: String
    ): Flow<State<SignupResponse?>> {
        return authRepository.signupLandowner(
            LandownerSignupRequest(
                role = "landowner",
                phone_number = phoneNumber,
                username = username,
                password = password
            )
        )
    }
}