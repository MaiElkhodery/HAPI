package com.example.hapi.presentation.auth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.landowner.CropRecommendationUseCase
import com.example.hapi.domain.usecase.sign.FarmerSignupUseCase
import com.example.hapi.domain.usecase.sign.LandownerSignupUseCase
import com.example.hapi.domain.usecase.sign.SigninUseCase
import com.example.hapi.domain.usecase.landowner.UploadSelectedCropUseCase
import com.example.hapi.util.Crop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signupFarmerUseCase: FarmerSignupUseCase,
    private val signupLandownerUseCase: LandownerSignupUseCase,
    private val signinUseCase: SigninUseCase,
    private val cropRecommendationUseCase: CropRecommendationUseCase,
    private val uploadSelectedCropUseCase: UploadSelectedCropUseCase
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

    private var _isLandowner = MutableStateFlow(false)
    var isLandowner = _isLandowner.asStateFlow()

    private val _recommendedCrops = MutableStateFlow(emptyList<Crop>())
    var recommendedCrops = _recommendedCrops.asStateFlow()

    private val _cropIsUploaded = MutableStateFlow(false)
    var cropIsUploaded = _cropIsUploaded.asStateFlow()

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
                        resetErrors(state.error as SignupErrorInfo)
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
                        Log.d("SIGNUP", "loading")
                    }

                    is State.Success -> {
                        Log.d("SIGNUP", "done")
                        _loading.value = false
                        _authenticated.value = true
                    }

                    is State.Error -> {
                        Log.d("SIGNUP", "error")
                        _loading.value = false
                        resetErrors(state.error as SignupErrorInfo)
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
                Log.d("SIGNIN",state.toString())
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
                        if (state.result!!.role == "landowner") {
                            _isLandowner.value = true
                        }
                        Log.d("SIGNIN", "done")

                    }

                    else -> {}
                }
            }
        }
    }

    fun getRecommendedCrops() {
        viewModelScope.launch {
            _errorMsg.value = ""
            cropRecommendationUseCase().collect { state ->
                when (state) {
                    is State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _recommendedCrops.value = state.result!!
                        Log.d("viewmodel", "Crops: ${state.result}")
                    }

                    is State.Error -> {
                        _loading.value = false
                        _errorMsg.value = state.error.message
                    }

                    else -> {}
                }
            }
        }
    }

    fun uploadSelectedCrop(
        crop: String
    ) {
        viewModelScope.launch {
            _errorMsg.value = ""
            uploadSelectedCropUseCase(crop).collect { state ->
                Log.d("UPLOAD", "$state")
                when (state) {
                    is State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _cropIsUploaded.value = true
                        _loading.value = false
                    }

                    is State.Error -> {
                        _loading.value = false
                        _errorMsg.value = state.error.message
                    }

                    else -> {}
                }
            }
        }
    }

    private fun resetErrors(error: SignupErrorInfo) {
        val phoneKey = "phone_number"
        val usernameKey = "username"
        val passwordKey = "password"
        error.errors.forEach { (key, list) ->
            when (key) {
                phoneKey -> _phoneNumberError.value = list[0]
                usernameKey -> _usernameError.value = list[0]
                passwordKey -> _passwordError.value = list[0]
            }

        }

    }
}