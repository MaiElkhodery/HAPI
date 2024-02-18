package com.example.hapi.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.model.SignupErrorInfo
import com.example.hapi.data.model.State
import com.example.hapi.domain.usecase.FarmerSignupUseCase
import com.example.hapi.domain.usecase.LandownerSignupUseCase
import com.example.hapi.domain.usecase.SigninUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signupFarmerUseCase: FarmerSignupUseCase,
    private val signupLandownerUseCase: LandownerSignupUseCase,
    private val signinUseCase: SigninUseCase
) : ViewModel() {

    private var _errorMsg = MutableStateFlow("")
    var errorMsg = _errorMsg.asStateFlow()

    private var _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    private var _passwordError = MutableStateFlow("")
    var passwordError = _passwordError.asStateFlow()

    private var _username = MutableStateFlow("")
    var username = _username.asStateFlow()

    private var _usernameError = MutableStateFlow("")
    var usernameError = _usernameError.asStateFlow()

    private var _landId = MutableStateFlow("")
    var landId = _landId.asStateFlow()

    private var _landIdError = MutableStateFlow("")
    var landIdError = _landIdError.asStateFlow()

    private var _phoneNumber = MutableStateFlow("")
    var phoneNumber = _phoneNumber.asStateFlow()

    private var _phoneNumberError = MutableStateFlow("")
    var phoneNumberError = _phoneNumberError.asStateFlow()


    private var _loading = MutableStateFlow(false)
    var loading = _loading.asStateFlow()

    private var _authenticated = MutableStateFlow(false)
    var authenticated = _authenticated.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.ChangeFarmId -> {
                _landId.value = event.farmId
            }

            is AuthEvent.ChangePassword -> {
                _password.value = event.password
            }

            is AuthEvent.ChangePhoneNumber -> {
                _phoneNumber.value = event.phoneNumber
            }

            is AuthEvent.ChangeUserName -> {
                _username.value = event.username
            }
        }
    }

    fun signupFarmer() {
        viewModelScope.launch {
            signupFarmerUseCase(
                phoneNumber = _phoneNumber.value,
                username = _username.value,
                password = _password.value,
                landId = _landId.value
            ).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _authenticated.value = true
                    }

                    is State.Error -> {
                        _loading.value = false
                        val error = state.error as SignupErrorInfo
                        error.errors.forEach { (ker, list) ->
                            when (ker) {
                                "phone_number" -> _phoneNumberError.value = list[0]
                                "username" -> _usernameError.value = list[0]
                                "land_id" -> _landIdError.value = list[0]
                                "password" -> _passwordError.value = list[0]
                            }

                        }
                    }

                    else -> {}
                }
            }
        }
    }

    fun signupLandowner() {
        viewModelScope.launch {
            signupLandownerUseCase(
                phoneNumber = _phoneNumber.value,
                username = _username.value,
                password = _password.value
            ).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _authenticated.value = true
                    }

                    is State.Error -> {
                        _loading.value = false
                        val error = state.error as SignupErrorInfo
                        error.errors.forEach { (key, list) ->
                            when (key) {
                                "phone_number" -> _phoneNumberError.value = list[0]
                                "username" -> _usernameError.value = list[0]
                                "password" -> _passwordError.value = list[0]
                            }

                        }
                    }

                    else -> {}
                }
            }
        }

    }

    fun signin() {
        viewModelScope.launch {
            signinUseCase(
                phoneNumber = _phoneNumber.value,
                password = _password.value
            ).collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                        _errorMsg.value = state.error.message
                    }

                    State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _authenticated.value = true
                    }

                    else -> {}
                }
            }
        }
    }


}