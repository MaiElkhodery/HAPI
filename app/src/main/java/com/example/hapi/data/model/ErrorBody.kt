package com.example.hapi.data.model

sealed class ErrorBody{
    data class LandownerSignupError(
        var usernameMsg: String = "",
        var passwordMsg: String = "",
        var phoneNumberMsg: String = ""
    ):ErrorBody()
}
