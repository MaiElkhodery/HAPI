package com.example.hapi.presentation.signup.farmersignup.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FarmerSignupViewModel @Inject constructor(

) : ViewModel() {
    private var _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    private var _passwordError = MutableStateFlow("")
    var passwordError = _passwordError.asStateFlow()

    private var _username = MutableStateFlow("")
    var username = _username.asStateFlow()

    private var _usernameError = MutableStateFlow("THIS USERNAME IS NOT VALID")
    var usernameError = _usernameError.asStateFlow()

    private var _farmId = MutableStateFlow("")
    var farmId = _farmId.asStateFlow()

    private var _farmIdError = MutableStateFlow("Not Valid")
    var farmIdError = _farmIdError.asStateFlow()

    private var _phoneNumber = MutableStateFlow("")
    var phoneNumber = _phoneNumber.asStateFlow()

    private var _phoneNumberError = MutableStateFlow("")
    var phoneNumberError = _phoneNumberError.asStateFlow()

    fun onEvent(event: FarmerSignupEvent) {
        when (event) {
            is FarmerSignupEvent.ChangeFarmId -> {
                _farmId.value = event.farmId
            }

            is FarmerSignupEvent.ChangePassword -> {
                _password.value = event.password
            }

            is FarmerSignupEvent.ChangePhoneNumber -> {
                _phoneNumber.value = event.phoneNumber
            }

            is FarmerSignupEvent.ChangeUsrName -> {
                _username.value = event.username
            }
        }
    }

    fun signup(){

    }
}