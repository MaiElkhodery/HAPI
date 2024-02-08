package com.example.hapi.presentation.signup.farmersignup.viewmodel

sealed class SignupEvent {
    data class ChangePhoneNumber(
        val phoneNumber:String
    ):SignupEvent()

    data class ChangeUsrName(
        val username:String
    ):SignupEvent()
    data class ChangePassword(
        val password:String
    ):SignupEvent()
    data class ChangeFarmId(
        val farmId:String
    ):SignupEvent()
}