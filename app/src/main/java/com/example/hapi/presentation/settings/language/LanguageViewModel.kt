package com.example.hapi.presentation.settings.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val userDataPreference: UserDataPreference,
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

}