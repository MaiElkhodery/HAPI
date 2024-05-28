package com.example.hapi.presentation.settings.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val userDataPreference: UserDataPreference
) : ViewModel() {

    private var _isEnglishIsSelected = MutableStateFlow(true)
    val isEnglishIsSelected = _isEnglishIsSelected

    fun setLanguage(
        isEnglish: Boolean
    ) {
        viewModelScope.launch {
            _isEnglishIsSelected.value = isEnglish
            userDataPreference.setLanguage(isEnglish)
        }
    }

    fun getLanguage() {
        viewModelScope.launch {
            _isEnglishIsSelected.value = userDataPreference.getLanguage()
        }
    }

    init {
        getLanguage()
    }
}