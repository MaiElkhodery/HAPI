package com.example.hapi.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.AuthPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authPreference: AuthPreference
) : ViewModel() {

    private val _role = MutableStateFlow<String?>(null)
    val role = _role.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()
    fun getRole() {
        viewModelScope.launch {
            _role.value = authPreference.getToken()
        }
    }

    fun getToken() {
        viewModelScope.launch {
            _token.value = authPreference.getRole()
        }
    }
}