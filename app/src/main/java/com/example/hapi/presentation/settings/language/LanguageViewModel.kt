package com.example.hapi.presentation.settings.language

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.util.ARABIC
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.LocaleHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val userDataPreference: UserDataPreference,
    private val localeHelper: LocaleHelper,
) : ViewModel() {

    private var _appLanguage = MutableStateFlow("")
    val appLanguage = _appLanguage.asStateFlow()

    fun getLanguage() {
        viewModelScope.launch {
            _appLanguage.value = userDataPreference.getLanguage()!!
        }
    }

    init {
        getLanguage()
    }

    fun onEvent(event: LanguageEvent) {
        when (event) {
            LanguageEvent.SetLanguageToArabic -> {
                viewModelScope.launch {
                    userDataPreference.setLanguage(ARABIC)
                    _appLanguage.value = ARABIC
                    val context = localeHelper.setLocale(ARABIC)
                }
            }

            LanguageEvent.SetLanguageToEnglish -> {
                viewModelScope.launch {
                    userDataPreference.setLanguage(ENGLISH)
                    _appLanguage.value = ENGLISH
                    val context = localeHelper.setLocale(ENGLISH)
                    Log.d("LANGUAGE VIEWMODEL", "")
                }
            }
        }
    }
}