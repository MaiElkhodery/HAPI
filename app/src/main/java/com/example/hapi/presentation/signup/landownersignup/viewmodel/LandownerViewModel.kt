package com.example.hapi.presentation.signup.landownersignup.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hapi.presentation.signup.farmersignup.viewmodel.SignupEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LandownerViewModel @Inject constructor(

) :ViewModel() {
    private var _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    private var _passwordError = MutableStateFlow("")
    var passwordError = _passwordError.asStateFlow()

    private var _username = MutableStateFlow("")
    var username = _username.asStateFlow()

    private var _usernameError = MutableStateFlow("THIS USERNAME IS NOT VALID")
    var usernameError = _usernameError.asStateFlow()

    private var _phoneNumber = MutableStateFlow("")
    var phoneNumber = _phoneNumber.asStateFlow()

    private var _phoneNumberError = MutableStateFlow("")
    var phoneNumberError = _phoneNumberError.asStateFlow()

    fun onEvent(event: SignupEvent) {
        when (event) {

            is SignupEvent.ChangePassword -> {
                _password.value = event.password
            }

            is SignupEvent.ChangePhoneNumber -> {
                _phoneNumber.value = event.phoneNumber
            }

            is SignupEvent.ChangeUsrName -> {
                _username.value = event.username
            }

            else -> {}
        }
    }
}