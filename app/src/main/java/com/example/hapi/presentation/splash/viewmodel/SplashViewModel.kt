package com.example.hapi.presentation.splash.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authPreference: UserDataPreference
) : ViewModel() {

    private val _role = MutableStateFlow<String?>(null)
    val role = _role.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    private val _isCropSelected = MutableStateFlow(false)
    val isCropSelected = _isCropSelected.asStateFlow()
    fun getRole() {
        viewModelScope.launch {
            _role.value = authPreference.getRole()
        }
    }

    fun getToken() {
        viewModelScope.launch {
            _token.value = authPreference.getToken()
        }
    }

    fun getIsCropSelected() {
        viewModelScope.launch {
            _isCropSelected.value = authPreference.getCrop().isNotBlank()
        }
    }
//    init {
//        getRole()
//        getToken()
//        getIsCropSelected()
//    }
}