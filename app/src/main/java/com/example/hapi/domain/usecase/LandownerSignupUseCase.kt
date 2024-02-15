package com.example.hapi.domain.usecase

import com.example.hapi.data.model.State
import com.example.hapi.data.remote.request.LandownerSignupRequest
import com.example.hapi.data.remote.response.AuthResponse
import com.example.hapi.data.repository.LandownerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LandownerSignupUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        username: String,
        password: String
    ): Flow<State<AuthResponse?>> {
        return landownerRepository.signup(
            LandownerSignupRequest(
                role = "landowner",
                phone_number = phoneNumber,
                username = username,
                password = password
            )
        )
    }
}