package com.example.hapi.domain.usecase

import com.example.hapi.data.remote.request.SigninRequest
import com.example.hapi.data.repository.AuthRepository
import javax.inject.Inject

class SigninUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(
        phoneNumber: String,
        password: String
    ) = authRepository.signin(
        SigninRequest(
            password = password,
            phone_number = phoneNumber
        )
    )

}