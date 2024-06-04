package com.example.hapi.presentation.settings.language

sealed class LanguageEvent {
    data object SetLanguageToArabic : LanguageEvent()
    data object SetLanguageToEnglish : LanguageEvent()
}