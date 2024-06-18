package com.example.hapi.domain.usecase.sign

import com.example.hapi.data.repository.AuthRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.deleteAccount()
}