package com.example.hapi.presentation.settings.landowner

sealed class LandownerSettingsEvent {
    data object OnClickLogout : LandownerSettingsEvent()

    data object OnClickDeleteAccount : LandownerSettingsEvent()
    data class ChangePassword(
        val password: String
    ) : LandownerSettingsEvent()
}