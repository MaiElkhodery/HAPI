package com.example.hapi.presentation.settings.farmer

sealed class FarmerSettingsEvent {
    data object OnClickLogout : FarmerSettingsEvent()

    data object OnClickDeleteAccount : FarmerSettingsEvent()
    data class ChangePassword(
        val password: String
    ) : FarmerSettingsEvent()
}