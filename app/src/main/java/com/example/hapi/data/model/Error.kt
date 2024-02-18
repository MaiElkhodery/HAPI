package com.example.hapi.data.model

sealed class ErrorInfo {
    abstract val message: String
}

data class SignupErrorInfo(
    override val message: String,
    val errors: Map<String, List<String>>
) : ErrorInfo()

data class SigninErrorInfo(
    override val message: String
) : ErrorInfo()