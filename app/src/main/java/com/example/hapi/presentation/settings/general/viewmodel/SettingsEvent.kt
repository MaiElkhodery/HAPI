package com.example.hapi.presentation.settings.general.viewmodel

sealed class SettingsEvent {
    data object OnClickLogout : SettingsEvent()

    data object OnClickDeleteAccount : SettingsEvent()
    data class ChangePassword(
        val password: String
    ) : SettingsEvent()
}