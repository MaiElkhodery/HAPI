package com.example.hapi.presentation.signup.landownersignup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.model.ErrorBody
import com.example.hapi.data.model.State
import com.example.hapi.domain.usecase.LandownerSignupUseCase
import com.example.hapi.presentation.signup.farmersignup.viewmodel.SignupEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandownerViewModel @Inject constructor(
    val signupUseCase: LandownerSignupUseCase
) : ViewModel() {
    private var _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    private var _passwordError = MutableStateFlow("")
    var passwordError = _passwordError.asStateFlow()

    private var _username = MutableStateFlow("")
    var username = _username.asStateFlow()

    private var _usernameError = MutableStateFlow("")
    var usernameError = _usernameError.asStateFlow()

    private var _phoneNumber = MutableStateFlow("")
    var phoneNumber = _phoneNumber.asStateFlow()

    private var _phoneNumberError = MutableStateFlow("")
    var phoneNumberError = _phoneNumberError.asStateFlow()

    private var _recommendedCrops = MutableStateFlow<List<String>>(emptyList())
    var recommendedCrops = _recommendedCrops.asStateFlow()

    private var _loading = MutableStateFlow(false)
    var loading = _loading.asStateFlow()

    private var _authenticated = MutableStateFlow(false)
    var authenticated = _authenticated.asStateFlow()

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

    fun signup() {
        viewModelScope.launch {
            signupUseCase(
                phoneNumber = _phoneNumber.value,
                username = _username.value,
                password = _password.value
            ).collect { state ->
                when (state) {
                    is State.Error -> {
                        val error = state.error as ErrorBody.LandownerSignupError
                        _phoneNumberError.value = error.phoneNumberMsg
                        _usernameError.value = error.usernameMsg
                        _passwordError.value = error.passwordMsg
                    }

                    is State.Exception -> TODO()
                    State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _authenticated.value=true
                    }
                }
            }
        }
    }

    fun confirm() {

    }

    fun cropRecommendation() {

    }
}