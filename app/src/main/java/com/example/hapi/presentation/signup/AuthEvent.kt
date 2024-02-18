package com.example.hapi.presentation.signup

sealed class AuthEvent {
    data class ChangePhoneNumber(
        val phoneNumber:String
    ):AuthEvent()

    data class ChangeUserName(
        val username:String
    ):AuthEvent()
    data class ChangePassword(
        val password:String
    ):AuthEvent()
    data class ChangeFarmId(
        val farmId:String
    ):AuthEvent()
}