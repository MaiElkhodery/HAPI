package com.example.hapi.presentation.signup.farmersignup.viewmodel

sealed class FarmerSignupEvent {
    data class ChangePhoneNumber(
        val phoneNumber:String
    ):FarmerSignupEvent()

    data class ChangeUsrName(
        val username:String
    ):FarmerSignupEvent()
    data class ChangePassword(
        val password:String
    ):FarmerSignupEvent()
    data class ChangeFarmId(
        val farmId:String
    ):FarmerSignupEvent()
}